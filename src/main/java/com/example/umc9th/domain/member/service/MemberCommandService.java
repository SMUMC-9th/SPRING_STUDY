package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.entity.Member;

public interface MemberCommandService {

    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
}
