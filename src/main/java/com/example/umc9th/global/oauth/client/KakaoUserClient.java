package com.example.umc9th.global.oauth.client;

import com.example.umc9th.global.config.FeignConfig;
import com.example.umc9th.global.oauth.dto.KakaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoUserClient",
        url = "https://kapi.kakao.com",
        configuration = FeignConfig.class
)
public interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    KakaoDTO.GetUserInfo getUserInfo(
            @RequestHeader("Authorization") String authorization
    );
}
