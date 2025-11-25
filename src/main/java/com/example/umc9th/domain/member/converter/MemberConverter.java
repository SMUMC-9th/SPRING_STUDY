package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.resposne.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.SignUpResponseDTO toSignUpResponseDTO(Member member){
        return MemberResponseDTO.SignUpResponseDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getUsername())
                .build();
    }

    public static MemberResponseDTO.LoginResponseDTO toLoginResponseDTO(
            String accessToken,
            String refreshToken
    ) {
        return MemberResponseDTO.LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
