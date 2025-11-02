package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    public Article toEntity(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .build();
    }

    public ArticleResponseDTO.CreateArticleResponseDTO toCreateResponse(Article article) {
        return ArticleResponseDTO.CreateArticleResponseDTO.builder()
            .id(article.getId())
            .title(article.getTitle())
            .createdAt(article.getCreatedAt())
            .build();
    }

    public ArticleResponseDTO.GetArticleResponseDTO toGetResponse(Article article) {
        return ArticleResponseDTO.GetArticleResponseDTO.builder()
            .id(article.getId())
            .title(article.getTitle())
            .content(article.getContent())
            .likeNum(article.getLikeNum())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
    }

    public ArticleResponseDTO.GetArticlesResponseDTO toGetListItemResponse(Article article) {
        return ArticleResponseDTO.GetArticlesResponseDTO.builder()
            .id(article.getId())
            .title(article.getTitle())
            .likeNum(article.getLikeNum())
            .createdAt(article.getCreatedAt())
            .build();
    }

    public List<ArticleResponseDTO.GetArticlesResponseDTO> toGetListResponse(List<Article> articles) {
        return articles.stream()
            .map(this::toGetListItemResponse)
            .collect(Collectors.toList());
    }

    public ArticleResponseDTO.UpdateArticleResponseDTO toUpdateResponse(Article article) {
        return ArticleResponseDTO.UpdateArticleResponseDTO.builder()
            .id(article.getId())
            .title(article.getTitle())
            .content(article.getContent())
            .updatedAt(article.getUpdatedAt())
            .build();
    }

    public ArticleResponseDTO.DeleteArticleResponseDTO toDeleteResponse(Article article) {
        return ArticleResponseDTO.DeleteArticleResponseDTO.builder()
            .id(article.getId())
            .deletedAt(article.getDeletedAt())
            .build();
    }
}