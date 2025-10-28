package com.example.umc9th.domain.reply.dto.response;

import java.time.LocalDateTime;

public class ReplyResponse {

    public record GetReplyResDTO(
            Long replyId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    public record GetReplyWithArticleIdResDTO(
            Long articleId,
            Long replyId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
