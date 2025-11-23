package com.example.umc9th.global.security.service;

import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.entity.Member;

public interface TokenCommandService {

    MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}
