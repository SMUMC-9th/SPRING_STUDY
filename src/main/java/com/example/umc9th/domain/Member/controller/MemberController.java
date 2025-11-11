package com.example.umc9th.domain.Member.controller;

import com.example.umc9th.domain.Member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.service.command.MemberCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.SignUpResponseDTO> signUp(
            @RequestBody MemberRequestDTO.SignUpRequestDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, memberCommandService.signUp(dto));
    }
}