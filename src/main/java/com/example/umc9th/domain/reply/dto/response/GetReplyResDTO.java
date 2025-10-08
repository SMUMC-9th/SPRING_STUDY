package com.example.umc9th.domain.reply.dto.response;

import java.time.LocalDateTime;

public record GetReplyResDTO(
        Long articleId,
        Long replyId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
