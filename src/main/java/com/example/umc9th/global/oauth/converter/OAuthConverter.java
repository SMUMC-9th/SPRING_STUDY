package com.example.umc9th.global.oauth.converter;

import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class OAuthConverter {

    public static MemberResDTO.Login toLoginDTO(
            String accessToken,
            String refreshToken,
            Member member
    ){
        return MemberResDTO.Login.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
