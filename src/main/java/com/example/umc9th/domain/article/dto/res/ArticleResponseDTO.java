package com.example.umc9th.domain.article.dto.res;

import lombok.Builder;

import java.util.List;

public class ArticleResponseDTO {

    // 게시글 생성
    @Builder
    public record CreateArticle(
            Long id,
            String title
    ){}

    // 특정 게시글 조회
    @Builder
    public record GetArticle(
            Long id,
            String title,
            String content
    ){}

    // 전체 게시글 조회
    @Builder
    public record GetAllArticles <T>(
        List<T> articles
    ){}

    // 게시글 수정
    @Builder
    public record UpdateArticle(
            Long id
    ){}

    // 게시글 삭제
    @Builder
    public record DeleteArticle(
            Long id
    ){}
}
