package com.example.umc9th.domain.article.dto.request;

import lombok.Builder;

public class ArticleRequestDTO {

    @Builder
    public record CreateArticleDTO (
            String title,
            String content
    ) {}
}