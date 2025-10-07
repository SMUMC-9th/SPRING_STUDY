package com.example.umc9th.domain.article.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {

    // 단일 게시글 상세 조회 응답 DTO
    @Builder
    public record ArticleDetailDTO(
            Long articleId,
            String title,
            String content,
            Integer likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

}
