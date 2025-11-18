package com.example.umc9th.global.auth.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCommandService{

    private final JwtUtil jwtUtil;

    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {

        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);

        return MemberConverter.toLoginResponseDTO(accessToken, refreshToken);
    }
}

