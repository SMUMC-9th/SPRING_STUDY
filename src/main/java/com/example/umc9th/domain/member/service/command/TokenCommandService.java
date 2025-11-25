package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;

public interface TokenCommandService {
    MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}