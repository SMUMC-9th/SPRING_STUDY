package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public class ArticleConverter {

    //요청 DTO -> 엔티티
    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .likeNum(0)
                .build();
    }

    //엔티티 -> 단건 응답 DTO
    public static ArticleResponseDTO.ArticleDTO toArticleDTO(Article article) {
        return ArticleResponseDTO.ArticleDTO.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .build();
    }

    //엔티티 -> 목록 DTO
    public static ArticleResponseDTO.ArticleSummaryDTO toSummaryDTO(Article article) {
        return ArticleResponseDTO.ArticleSummaryDTO.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .likeNum(article.getLikeNum())
                .build();
    }

    //엔티티 리스트 -> 목록 응답 DTO
    public static ArticleResponseDTO.ArticleListDTO toListDTO(List<Article> articles) {
        return ArticleResponseDTO.ArticleListDTO.builder()
                .articles(articles.stream().map(ArticleConverter::toSummaryDTO).toList())
                .build();
    }

}
