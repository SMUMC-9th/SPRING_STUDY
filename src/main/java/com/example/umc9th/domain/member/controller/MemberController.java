package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
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
    public ApiResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        MemberResponseDTO.SignUpResponseDTO responseDTO = MemberResponseDTO.SignUpResponseDTO.from(member);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDTO);
    }
}