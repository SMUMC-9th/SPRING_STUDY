package com.example.umc9th.domain.article.dto.request;

import lombok.Getter;


public class ArticleRequestDTO {
    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;
    }
}
