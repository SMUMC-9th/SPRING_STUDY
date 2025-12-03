package com.example.umc9th.global.oauth.controller;

import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.oauth.dto.KakaoDTO;
import com.example.umc9th.global.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    //
    // http://localhost:8080/oauth2/authorization/kakao
    //
    // 콜백 컨트롤러
    @GetMapping("/oauth2/callback/kakao")
    public ApiResponse<MemberResDTO.Login> kakaoLogin(
            @RequestParam("code") String code
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, oAuthService.kakaoLogin(code));
    }
}
