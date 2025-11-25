package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;

public interface MemberCommandService {
    // 회원 가입
    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto);

    //로그인
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);
}
