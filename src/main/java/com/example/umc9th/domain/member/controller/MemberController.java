package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.command.OAuth2Service;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/auth/sign-up")
    public ApiResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        MemberResponseDTO.SignUpResponseDTO responseDTO = MemberResponseDTO.SignUpResponseDTO.from(member);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDTO);
    }

    @PostMapping("/auth/login")
    public ApiResponse<MemberResponseDTO.LoginResponseDTO> signin(@RequestBody MemberRequestDTO.LoginRequestDTO dto){
        MemberResponseDTO.LoginResponseDTO responseDTO = memberCommandService.login(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDTO);
    }
    @GetMapping("/oauth2/callback/kakao")
    public ApiResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(@RequestParam("code") String code) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, oAuth2Service.login(code));
    }
}