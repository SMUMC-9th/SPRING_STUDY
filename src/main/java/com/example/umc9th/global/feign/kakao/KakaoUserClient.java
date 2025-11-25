package com.example.umc9th.global.feign.kakao;

import com.example.umc9th.domain.Member.dto.res.OAuth2DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakaoUserClient",
        url = "https://kapi.kakao.com"
)
public interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    OAuth2DTO.KakaoProfile getUserInfo(
            @RequestHeader("Authorization") String bearerToken
    );
}
