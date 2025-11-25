package com.example.umc9th.global.auth.service;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}
