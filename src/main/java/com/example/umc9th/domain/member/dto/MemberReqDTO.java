package com.example.umc9th.domain.member.dto;

public class MemberReqDTO {

    public record Login(
            String userId,
            String password
    ){}
}
