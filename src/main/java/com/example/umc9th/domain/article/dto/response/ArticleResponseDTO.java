package com.example.umc9th.domain.article.dto.response;

import com.example.umc9th.domain.article.entity.Article;

public record ArticleResponseDTO(
        Long id,
        String title,
        String content
) {
    public static ArticleResponseDTO from(Article article){
        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getContent()
        );
    }
}
