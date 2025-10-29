package com.example.umc9th.domain.reply.dto.req;

import lombok.Builder;

public class ReplyRequestDTO {

    @Builder
    public record CreateReply(
            String content,
            Long articleId
    ){}

    // 댓글 수정
    public record UpdateReply(
            Long articleId,
            String content
    ){}
}
