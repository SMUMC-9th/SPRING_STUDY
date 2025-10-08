package com.example.umc9th.domain.article.dto.request;

import com.example.umc9th.domain.article.entity.Article;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;

        public Article toEntity() {
            return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
        }
    }
}