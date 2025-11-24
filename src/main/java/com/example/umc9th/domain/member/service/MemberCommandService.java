package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.service.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;

    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        return memberRepository.save(
                Member.builder()
                        .username(dto.username())
                        .password(passwordEncoder.encode(dto.password()))
                        .build()
        );
    }

    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {
        Member member = memberRepository.findByUsername(dto.username()).orElseThrow(() ->
                new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_REQUEST);
        }

        return tokenCommandService.createLoginToken(member);
    }


}