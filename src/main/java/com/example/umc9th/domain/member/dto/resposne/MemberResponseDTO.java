package com.example.umc9th.domain.member.dto.resposne;

import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record SignUpResponseDTO(Long memberId, String email, String name) {}

    @Builder
    public record LoginResponseDTO(String accessToken, String refreshToken) {}


}
