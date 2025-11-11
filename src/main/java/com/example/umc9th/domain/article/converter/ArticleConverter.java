package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    // 1. DTO -> Entity (게시글 생성 시)
    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO request) {
        return Article.builder()
                .title(request.title())
                .content(request.content())
                .build();
    }


    public static ArticleResponseDTO.ArticleDetailDTO toDetailDTO(Article article) {
        return ArticleResponseDTO.ArticleDetailDTO.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // DTO -> Entity 업데이트
    public static ArticleResponseDTO.UpdateArticleResDTO toUpdateResDTO(Article article) {
        return ArticleResponseDTO.UpdateArticleResDTO.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // Entity -> DTO
    public static ArticleResponseDTO.ArticleCursorDTO toCursorPageDTO(List<Article> articles) {
        List<ArticleResponseDTO.ArticleDetailDTO> dtoList = articles.stream()
                .map(ArticleConverter::toDetailDTO)
                .collect(Collectors.toList());

        // 다음 커서는 마지막 게시글의 likeNum 기준
        String nextCursor = articles.isEmpty()
                ? null
                : String.valueOf(articles.get(articles.size() - 1).getLikeNum());

        return ArticleResponseDTO.ArticleCursorDTO.builder()
                .articles(dtoList)
                .nextCursor(nextCursor)
                .build();
    }
}
