package com.example.umc9th.global.auth.service.command;

import com.example.umc9th.domain.member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenCommandServiceImpl implements TokenCommandService{

    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member){
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(jwtUtil.createAccessToken(member))
                .refreshToken(jwtUtil.createRefreshToken(member))
                .build();
    }
}
