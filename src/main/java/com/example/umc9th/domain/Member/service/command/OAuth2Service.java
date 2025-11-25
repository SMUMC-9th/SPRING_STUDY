package com.example.umc9th.domain.Member.service.command;

import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.LoginResponseDTO login(String code);
}
