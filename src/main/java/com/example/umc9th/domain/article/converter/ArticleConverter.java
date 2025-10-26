package com.example.umc9th.domain.article.converter;


import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

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

    // 객체 -> DTO
    public static ArticleResponseDTO.CreateArticle toCreateArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.CreateArticle.builder()
                .id(article.getId())
                .title(article.getTitle())
                .build();
    }

    // 특정 게시글 조회
    public static ArticleResponseDTO.GetArticle toGetArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.GetArticle.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    // 전체 게시글 조회
    public static <T> ArticleResponseDTO.GetAllArticles<T> toGetAllArticlesDTO(
            List<T> articles
    ){
        return ArticleResponseDTO.GetAllArticles.<T>builder()
                .articles(articles)
                .build();
    }

    // 게시글 수정
    public static ArticleResponseDTO.UpdateArticle toUpdateArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.UpdateArticle.builder()
                .id(article.getId())
                .build();
    }
}
