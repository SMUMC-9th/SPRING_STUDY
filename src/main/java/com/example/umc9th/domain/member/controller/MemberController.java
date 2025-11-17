package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @GetMapping("/sign-up")
    public ApiResponse<Void> signUp() {
        // 비밀번호 인코딩
        String pwd = encoder.encode("string");
        // 리포지토리 저장
        memberRepository.save(Member.builder().email("string").password(pwd).build());
        // 응답
        return ApiResponse.ok();
    }
}
