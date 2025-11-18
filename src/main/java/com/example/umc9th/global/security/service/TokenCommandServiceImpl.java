package com.example.umc9th.global.security.service;

import com.example.umc9th.domain.Member.converter.MemberConverter;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.entity.Member;
import com.example.umc9th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCommandServiceImpl implements TokenCommandService {

    private final JwtUtil jwtUtil;

    @Override
    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        String accessToken = jwtUtil.createAccessToken(member);

        String refreshToken = jwtUtil.createRefreshToken(member);

        return MemberConverter.toLoginResponseDTO(member, accessToken, refreshToken);
    }
}
