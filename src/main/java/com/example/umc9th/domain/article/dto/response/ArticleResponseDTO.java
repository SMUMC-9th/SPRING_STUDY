package com.example.umc9th.domain.article.dto.response;

import com.example.umc9th.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleResponseDTO {

    // 게시글 생성 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateArticleResponseDTO {
        private Long id;
        private String title;
        private LocalDateTime createdAt;

        public static CreateArticleResponseDTO from(Article article) {
            return CreateArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .build();
        }
    }

    // 게시글 단건 조회 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetArticleResponseDTO {
        private Long id;
        private String title;
        private String content;
        private Integer likeNum;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static GetArticleResponseDTO from(Article article) {
            return GetArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
        }
    }

    // 게시글 목록 조회 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetArticlesResponseDTO {
        private Long id;
        private String title;
        private Integer likeNum;
        private LocalDateTime createdAt;

        public static GetArticlesResponseDTO from(Article article) {
            return GetArticlesResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .build();
        }

        public static List<GetArticlesResponseDTO> fromList(List<Article> articles) {
            return articles.stream()
                .map(GetArticlesResponseDTO::from)
                .collect(Collectors.toList());
        }
    }
}