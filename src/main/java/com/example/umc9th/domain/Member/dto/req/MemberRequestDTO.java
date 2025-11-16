package com.example.umc9th.domain.Member.dto.req;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequestDTO {
        private String username;
        private String password;
    }
}
