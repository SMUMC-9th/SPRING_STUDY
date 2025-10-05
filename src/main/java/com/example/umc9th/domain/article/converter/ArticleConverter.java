package com.example.umc9th.domain.article.converter;


import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;

public class ArticleConverter {

    // 게시글 생성
    // DTO -> 객체
    public static Article toArticle(
        ArticleRequestDTO.CreateArticleDTO dto
    ){
        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .build();
    }
}
