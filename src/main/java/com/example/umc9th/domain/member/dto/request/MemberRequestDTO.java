package com.example.umc9th.domain.member.dto.request;

public class MemberRequestDTO {

    public record SignUpRequestDTO(String username, String password) {}

    public record LoginRequestDTO(String username, String password) {}

}
