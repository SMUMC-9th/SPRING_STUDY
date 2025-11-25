package com.example.umc9th.global.auth.controller;

import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.global.apiPayload.GlobalResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.auth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    public GlobalResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(
            @RequestParam("code") String code
    ) {
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, oAuth2Service.login(code));
    }
}
