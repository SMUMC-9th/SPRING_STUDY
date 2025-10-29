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
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateArticleResponseDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteArticleResponseDTO {
        private Long id;
        private LocalDateTime deletedAt;
    }
}