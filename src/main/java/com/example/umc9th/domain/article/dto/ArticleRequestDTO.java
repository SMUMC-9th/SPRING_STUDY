package com.example.umc9th.domain.article.dto;

import lombok.Builder;

//게시글 생성 요청 DTO
public class ArticleRequestDTO {
    @Builder
    public record CreateArticleDTO(
            String title,
            String content
    ) {}
}
