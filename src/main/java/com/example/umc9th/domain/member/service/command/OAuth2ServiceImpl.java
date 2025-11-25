package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.dto.response.OAuth2DTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret:}")
    private String clientSecret;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResponseDTO.LoginResponseDTO login(String code) {
        // 1. 인가 코드로 토큰 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", code);

        if (clientSecret != null && !clientSecret.isEmpty()) {
            map.add("client_secret", clientSecret);
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> response1;
        try {
            response1 = restTemplate.exchange(
                tokenURI,
                HttpMethod.POST,
                request,
                String.class);
        } catch (HttpClientErrorException e) {
            log.error("Kakao Token Exchange Error: {}", e.getResponseBodyAsString());
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO;
        try {
            oAuth2TokenDTO = objectMapper.readValue(response1.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }

        // 2. 토큰으로 사용자 정보 가져오기
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + oAuth2TokenDTO.getAccess_token());
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response2 = restTemplate.exchange(
            userInfoURI,
            HttpMethod.GET,
            request1,
            String.class);

        OAuth2DTO.KakaoProfile profile;
        ObjectMapper om = new ObjectMapper();
        try {
            profile = om.readValue(response2.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }

        // 3. 회원가입 또는 로그인 처리
        // 카카오 ID를 email로 사용 (이메일이 없는 경우 대비 fallback, 있으면 카카오에 등록된 이메일을 사용)
        String email = profile.getId().toString() + "@kakao.com";
        if (profile.getKakao_account() != null && profile.getKakao_account().getEmail() != null) {
            email = profile.getKakao_account().getEmail();
        }
        String username = profile.getProperties() != null
            ? profile.getProperties().getNickname() + "_" + profile.getId()
            : "KakaoUser_" + profile.getId();

        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            member = memberRepository.findByUsername(username).orElse(null);
        }

        if (member == null) {
            try {
                member = memberRepository.save(Member.builder()
                    .email(email)
                    .username(username)
                    .password("")
                    .role("ROLE_USER")
                    .build());
            } catch (DataIntegrityViolationException e) {
                member = memberRepository.findByEmail(email)
                    .orElseGet(() -> memberRepository.findByUsername(username)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_SAVE_FAIL))); // 적절한 에러
                // 코드로 변경 필요
            }
        }

        // 4. JWT 토큰 생성 및 반환
        return MemberResponseDTO.LoginResponseDTO.builder()
            .id(member.getId())
            .accessToken(jwtUtil.createAccessToken(member))
            .refreshToken(jwtUtil.createRefreshToken(member))
            .build();
    }
}