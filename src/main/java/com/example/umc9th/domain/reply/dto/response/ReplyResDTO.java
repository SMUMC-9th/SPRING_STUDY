package com.example.umc9th.domain.reply.dto.response;

import java.time.LocalDateTime;

public record ReplyResDTO(
        Long replyId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
