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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleDTO {
        private Long id;
        private String title;
        private String content;
        private Integer likeNum;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticleDTO from(Article article) {
            return ArticleDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
        }

        public static List<ArticleDTO> fromList(List<Article> articles) {
            return articles.stream()
                .map(ArticleDTO::from)
                .collect(Collectors.toList());
        }
    }
}
