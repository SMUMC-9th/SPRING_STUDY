package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberResDTO;

public class MemberConverter {

    // 로그인
    public static MemberResDTO.Login toLoginDTO(
            Long id,
            String accessToken,
            String refreshToken
    ){
        return MemberResDTO.Login.builder()
                .id(id)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
