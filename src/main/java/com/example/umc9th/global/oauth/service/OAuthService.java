package com.example.umc9th.global.oauth.service;

import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.oauth.client.KakaoAuthClient;
import com.example.umc9th.global.oauth.client.KakaoUserClient;
import com.example.umc9th.global.oauth.converter.OAuthConverter;
import com.example.umc9th.global.oauth.dto.KakaoDTO;
import com.example.umc9th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    // Value
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") String redirectUri;

    public MemberResDTO.Login kakaoLogin(
            String code
    ) {
        // 인가 코드로 카카오 엑세스 토큰 발급
        String accessToken = kakaoAuthClient.kakaoLogin(
                "authorization_code",
                clientId,
                redirectUri,
                code
        ).access_token();
        // 엑세스 토큰으로 유저 정보 조회
        String uid = kakaoUserClient.getUserInfo(
                "Bearer "+accessToken
        ).id().toString();
        // 회원가입이 되어있는지 확인
        Member member = memberRepository.findByUid(uid)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // 우리의 JWT토큰 발급
        return OAuthConverter.toLoginDTO(
                jwtUtil.createAccessToken(member),
                jwtUtil.createRefreshToken(member),
                member
        );
    }
}
