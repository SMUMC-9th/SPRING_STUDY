package com.example.umc9th.global.auth.controller;

import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.auth.service.OAuth2Service;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증/인가 관련 API")
public class AuthController {
    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public ApiResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        // 토큰 발급
        MemberResponseDTO.MemberTokenDTO memberTokenDTO = oAuth2Service.login(code);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberTokenDTO);
    }
}
