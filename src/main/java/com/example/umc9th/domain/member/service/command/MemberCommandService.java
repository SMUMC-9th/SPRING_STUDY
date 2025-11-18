package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.member.entity.Member;

public interface MemberCommandService {
    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
}
