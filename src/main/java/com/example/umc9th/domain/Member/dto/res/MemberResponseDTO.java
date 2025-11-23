package com.example.umc9th.domain.Member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class SignUpResponseDTO {
        private Long id;
    }

    @Getter
    @Builder
    public static class LoginResponseDTO {
        private Long id;
        private String accessToken;
        private String refreshToken;
    }
}
