package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final MemberService memberService;

    @GetMapping("/sign-up")
    public ApiResponse<Void> signUp() {
        // 비밀번호 인코딩
        String pwd = encoder.encode("string");
        // 리포지토리 저장
        memberRepository.save(Member.builder().email("string").password(pwd).build());
        // 응답
        return ApiResponse.ok();
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody MemberReqDTO.Login dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, memberService.login(dto));
    }
}
