package com.example.umc9th.global.auth.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberRole;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.dto.res.OAuth2DTO;
import com.example.umc9th.global.auth.exception.AuthException;
import com.example.umc9th.global.auth.exception.code.AuthErrorCode;
import com.example.umc9th.global.auth.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OAuth2Service{

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI; // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI; // 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId; // API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI; // 설정한 Redirect uri

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public MemberResponseDTO.LoginResponseDTO login(String code) {
        OAuth2DTO.OAuth2TokenDTO tokenDTO = requestToken(code);
        OAuth2DTO.KakaoProfile profile = requestUserProfile(tokenDTO.getAccess_token());

        String email = profile.getKakao_account().getEmail();

        if (email == null) {
            throw new AuthException(AuthErrorCode.OAUTH_EMAIL_NOT_FOUND);
        }

        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .email(email)
                                .role(MemberRole.ROLE_USER)
                                .build()
                ));

        return MemberConverter.toLoginResponseDTO(
                jwtUtil.createAccessToken(member),
                jwtUtil.createRefreshToken(member)
        );
    }

    // 토큰 요청
    private OAuth2DTO.OAuth2TokenDTO requestToken(String code) {

        try {
            String response = WebClient.builder().build().post()
                    .uri(tokenURI)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .bodyValue(
                            "grant_type=authorization_code" +
                                    "&client_id=" + clientId +
                                    "&redirect_uri=" + redirectURI +
                                    "&code=" + code
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return objectMapper.readValue(response, OAuth2DTO.OAuth2TokenDTO.class);

        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 사용자 정보 조회
    private OAuth2DTO.KakaoProfile requestUserProfile(String accessToken) {
        try {
            String response = WebClient.builder().build().get()
                    .uri(userInfoURI)
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return objectMapper.readValue(response, OAuth2DTO.KakaoProfile.class);

        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }
}
