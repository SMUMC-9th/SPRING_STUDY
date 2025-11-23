package com.example.umc9th.domain.Member.service.command;

import com.example.umc9th.domain.Member.converter.MemberConverter;
import com.example.umc9th.domain.Member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.entity.Member;
import com.example.umc9th.domain.Member.exception.MemberException;
import com.example.umc9th.domain.Member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.Member.repository.MemberRepository;
import com.example.umc9th.global.security.service.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;

    @Override
    public MemberResponseDTO.SignUpResponseDTO signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = MemberConverter.toMember(dto, passwordEncoder);
        memberRepository.save(member);
        return MemberConverter.toSignUpResponseDTO(member);
    }

    @Override
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {
        Member member = memberRepository.findByUsername(dto.getUsername())
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_CREDENTIAL);
        }

        return tokenCommandService.createLoginToken(member);
    }
}
