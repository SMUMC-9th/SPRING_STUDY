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
            String content,
            Integer likeNum
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

    // 전체 게시글 조회: JPA
    @Builder
    public record GetArticles(
            List<GetArticle> articles,
            String cursor,
            boolean hasNext
    ){}

    // 전체 게시글 조회: QueryDSL
    @Builder
    public record GetArticlesQueryDsl(
            List<GetArticle> articles,
            String cursor,
            Integer pageSize,
            Boolean hasNext
    ){}

    // 페이지네이션 틀
    @Builder
    public record Page<T>(
            List<T> result,
            String cursor,
            Integer pageSize,
            Boolean hasNext
    ){}

    // 게시글 검색
    @Builder
    public record SearchArticle(
            List<GetArticle> articles
    ){}
}
