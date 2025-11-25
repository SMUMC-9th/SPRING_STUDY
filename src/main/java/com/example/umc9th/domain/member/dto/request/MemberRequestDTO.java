package com.example.umc9th.domain.member.dto.request;

public class MemberRequestDTO {

    public record SignUpRequestDTO(String username, String email, String password) {}

    public record LoginRequestDTO(String username, String email, String password) {}

}
