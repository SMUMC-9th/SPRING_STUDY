package com.example.umc9th.domain.Member.dto.res;

import com.example.umc9th.domain.Member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class SignUpResponseDTO {
        private Long id;
        public static SignUpResponseDTO from(Member member){
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }
}
