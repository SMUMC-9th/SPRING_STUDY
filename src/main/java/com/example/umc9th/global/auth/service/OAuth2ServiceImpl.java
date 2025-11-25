package com.example.umc9th.global.auth.service;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.dto.OAuth2DTO;
import com.example.umc9th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient; // Bean으로 등록된 WebClient 주입

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {

        // 인가 코드로 토큰 요청 (POST)
        MultiValueMap<String, String> tokenRequestParams = new LinkedMultiValueMap<>();
        tokenRequestParams.add("grant_type", "authorization_code");
        tokenRequestParams.add("client_id", clientId);
        tokenRequestParams.add("redirect_uri", redirectURI);
        tokenRequestParams.add("code", code);


        OAuth2DTO.OAuth2TokenDTO tokenDTO = webClient.post()
                .uri(tokenURI)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(tokenRequestParams))
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL)))
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .block(); // 동기처리를 위해 block사용

        if (tokenDTO == null) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }

        // 토큰으로 사용자 정보 요청
        OAuth2DTO.KakaoProfile profile = webClient.post() // Kakao 문서는 POST/GET 둘 다 지원
                .uri(userInfoURI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDTO.getAccess_token())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL)))
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .block();

        if (profile == null) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }

        // 회원가입 및 로그인 처리
        String email = String.valueOf(profile.getId());


        Member member = memberRepository.findByEmail(email).orElseGet(() ->
                memberRepository.save(Member.builder()
                        .email(email)
                        .role("ROLE_USER")
                        .build())
        );

        //  JWT 반환
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtUtil.createAccessToken(member))
                .refreshToken(jwtUtil.createRefreshToken(member))
                .build();
    }
}