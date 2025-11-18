package com.example.umc9th.domain.Member.converter;

import com.example.umc9th.domain.Member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;
import com.example.umc9th.domain.Member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.SignUpRequestDTO dto, PasswordEncoder encoder) {
        return Member.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .build();
    }

    public static MemberResponseDTO.SignUpResponseDTO toSignUpResponseDTO(Member member) {
        return MemberResponseDTO.SignUpResponseDTO.builder()
                .id(member.getId())
                .build();
    }

    public static MemberResponseDTO.LoginResponseDTO toLoginResponseDTO(
            Member member, String accessToken, String refreshToken) {

        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
