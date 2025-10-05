package com.example.umc9th.domain.reply.dto.res;

import lombok.Builder;

public class ReplyResponseDTO {

    @Builder
    public record CreateReply(
            Long id,
            String content
    ){}
}
