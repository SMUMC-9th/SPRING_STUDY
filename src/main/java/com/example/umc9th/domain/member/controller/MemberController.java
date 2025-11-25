package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.command.OAuth2Service;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberResponseDTO.SignUpResponseDTO.from(member));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인하여 JWT 토큰을 발급받습니다.")
    public ApiResponse<MemberResponseDTO.LoginResponseDTO> login(
        @RequestBody MemberRequestDTO.LoginRequestDTO dto) {
        MemberResponseDTO.LoginResponseDTO response = memberCommandService.login(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping("/oauth2/callback/kakao")
    @Operation(summary = "카카오 OAuth2 콜백", description = "카카오 로그인 후 인가 코드를 받아 JWT 토큰을 발급합니다.")
    public ApiResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(@RequestParam("code") String code) {
        MemberResponseDTO.LoginResponseDTO response = oAuth2Service.login(code);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}