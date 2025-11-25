package com.example.umc9th.global.feign.kakao;

import com.example.umc9th.domain.Member.dto.res.OAuth2DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakaoAuthClient",
        url = "https://kauth.kakao.com"
)
public interface KakaoAuthClient {

    @PostMapping(
            value = "/oauth/token",
            consumes = "application/x-www-form-urlencoded"
    )
    OAuth2DTO.OAuth2TokenDTO getToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code
    );
}
