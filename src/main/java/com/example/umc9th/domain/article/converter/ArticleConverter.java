package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {

    public Article toEntity(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .build();
    }
}