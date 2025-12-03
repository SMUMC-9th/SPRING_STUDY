package com.example.umc9th.global.oauth.dto;

import lombok.Builder;

public class KakaoDTO {

    public record Login(
            String token_type,
            String access_token,
            Integer expires_in,
            String refresh_token,
            Integer refresh_token_expires_in,
            // 예외
            String error
    ){}

    public record GetUserInfo(
            Long id,
            KakaoAccount kakao_account
    ){}

    public record KakaoAccount(
            String email
    ){}
}
