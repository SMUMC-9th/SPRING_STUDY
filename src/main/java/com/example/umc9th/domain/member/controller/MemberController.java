package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.MemberCommandService;
import com.example.umc9th.global.apiPayload.GlobalResponse;
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
    public GlobalResponse<MemberResponseDTO.SignUpResponseDTO> signUp(
            @RequestBody MemberRequestDTO.SignUpRequestDTO dto
    ){
        Member member = memberCommandService.signUp(dto);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, MemberConverter.toSignUpResponseDTO(member));
    }

    @PostMapping("/login")
    public GlobalResponse<MemberResponseDTO.LoginResponseDTO> login(
            @RequestBody MemberRequestDTO.LoginRequestDTO dto
    ){
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, memberCommandService.login(dto));
    }
}
