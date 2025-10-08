package com.example.umc9th.domain.article.dto.response;

import java.time.LocalDateTime;

public record GetArticleResDTO(
    Long articleId,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    int likeNum
) {
}
