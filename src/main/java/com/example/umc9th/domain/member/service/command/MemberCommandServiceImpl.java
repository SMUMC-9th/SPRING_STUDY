package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.security.token.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;


    @Override
    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        return memberRepository.save(
                Member.builder()
                        .username(dto.getUsername())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .build()
        );
    }

    @Override
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {
        Member member = memberRepository.findByUsername(dto.username()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_CREDENTIAL);
        }
        return tokenCommandService.createLoginToken(member); // 유저 정보로 토큰만들기, 참고로 DTO에는 id, accessToken, refreshToken이 존재합니다.
    }

}
