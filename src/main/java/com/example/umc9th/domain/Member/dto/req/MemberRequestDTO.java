package com.example.umc9th.domain.Member.dto.req;

import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequestDTO {
        private String username;
        private String password;
    }

    @Getter
    public static class LoginRequestDTO {
        private String username;
        private String password;
    }
}
