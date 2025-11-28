package com.example.umc9th.domain.Member.service.command;

import com.example.umc9th.domain.Member.converter.MemberConverter;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.dto.res.OAuth2DTO;
import com.example.umc9th.domain.Member.entity.Member;
import com.example.umc9th.domain.Member.enums.Role;
import com.example.umc9th.domain.Member.exception.MemberException;
import com.example.umc9th.domain.Member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.Member.repository.MemberRepository;
import com.example.umc9th.global.feign.kakao.KakaoAuthClient;
import com.example.umc9th.global.feign.kakao.KakaoUserClient;
import com.example.umc9th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtutil;

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;

    private final PasswordEncoder passwordEncoder;


    @Override
    public MemberResponseDTO.LoginResponseDTO login(String code) {

        OAuth2DTO.OAuth2TokenDTO tokenDTO = kakaoAuthClient.getToken(
                "authorization_code",
                clientId,
                redirectURI,
                code
        );

        if(tokenDTO == null || tokenDTO.getAccessToken() == null) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }

        OAuth2DTO.KakaoProfile profile =
                kakaoUserClient.getUserInfo("Bearer " + tokenDTO.getAccessToken());

        if (profile == null || profile.getId() == null) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }

        String email = profile.getKakaoAccount().getEmail();

        if(email == null) {
            throw new MemberException(MemberErrorCode.OAUTH_EMAIL_NOT_FOUND);
        }


        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .email(email)
                                .username("kakao_" + UUID.randomUUID())
                                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                                .role(Role.USER)
                                .build()
                ));

        return MemberConverter.toLoginResponseDTO(member, jwtutil);
    }
}
