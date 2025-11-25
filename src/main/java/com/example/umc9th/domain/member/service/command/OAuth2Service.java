package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.LoginResponseDTO login(String code);
}