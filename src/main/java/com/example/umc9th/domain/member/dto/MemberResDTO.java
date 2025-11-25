package com.example.umc9th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record Login(
            Long id,
            String accessToken,
            String refreshToken
    ){}
}
