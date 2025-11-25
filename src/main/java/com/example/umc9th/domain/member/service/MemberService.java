package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public MemberResDTO.Login login(
            MemberReqDTO.Login dto
    ) {
        // 사용자 정보 가져오기
        Member member = memberRepository.findByEmail(dto.userId()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 맞는 지 검증
        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_CREDENTIAL);
        }

        // 사용자 정보를 토대로 JWT 생성 -> DTO로 만들어서 응답
        return MemberConverter.toLoginDTO(
                member.getId(),
                jwtUtil.createAccessToken(member),
                jwtUtil.createRefreshToken(member)
        );
    }
}
