package com.example.umc9th.domain.reply.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReplyResponseDTO {

    @Builder
    public record ReplyResDTO(
            Long replyId,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
