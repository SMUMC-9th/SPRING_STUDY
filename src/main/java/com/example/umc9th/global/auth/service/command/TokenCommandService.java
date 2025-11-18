package com.example.umc9th.global.auth.service.command;

import com.example.umc9th.domain.member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;

public interface TokenCommandService {
    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}
