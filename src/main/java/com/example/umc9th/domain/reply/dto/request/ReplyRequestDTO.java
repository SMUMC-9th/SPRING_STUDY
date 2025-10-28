package com.example.umc9th.domain.reply.dto.request;

import lombok.Builder;

public class ReplyRequestDTO {

    @Builder
    public record CreateReplyDTO(
            Long articleId,
            String content
    ) {}

    @Builder
    public record UpdateReplyDTO(
            String content
    ) {}
}
