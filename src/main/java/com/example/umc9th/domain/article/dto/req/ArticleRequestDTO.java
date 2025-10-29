package com.example.umc9th.domain.article.dto.req;

import lombok.Builder;

public class ArticleRequestDTO {

    // 게시글 생성
    @Builder
    public record CreateArticleDTO(
            String title,
            String content
    ){}

    // 게시글 수정
    public record UpdateArticleDTO(
            String title,
            String content
    ){}
}
