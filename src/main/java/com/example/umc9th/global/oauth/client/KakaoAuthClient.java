package com.example.umc9th.global.oauth.client;

import com.example.umc9th.global.config.FeignConfig;
import com.example.umc9th.global.oauth.dto.KakaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "KakaoAuthClient",
        url = "https://kauth.kakao.com",
        configuration = FeignConfig.class
)
public interface KakaoAuthClient {

    @PostMapping("/oauth/token")
    KakaoDTO.Login kakaoLogin(
            @RequestParam("grant_type") String grant_type,
            @RequestParam("client_id") String client_id,
            @RequestParam("redirect_uri") String redirect_uri,
            @RequestParam("code") String code
    );
}
