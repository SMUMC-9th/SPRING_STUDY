package com.example.umc9th.domain.article.dto.req;

import lombok.Builder;

public class ArticleRequestDTO {

    @Builder
    public record CreateArticleDTO(
            String title,
            String content
    ){}
}
