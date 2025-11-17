package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final TokenCommandService tokenCommandService;
    private final PasswordEncoder passwordEncoder;

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
        Member member = memberRepository.findByUsername(dto.getUsername())
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_CREDENTIAL);
        }

        return tokenCommandService.createLoginToken(member);
    }
}