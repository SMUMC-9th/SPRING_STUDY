package com.example.umc9th.domain.reply.dto.req;

import lombok.Builder;

public class ReplyRequestDTO {

    @Builder
    public record CreateReply(
            String content,
            Long articleId
    ){}
}
