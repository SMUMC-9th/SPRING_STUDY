package com.example.umc9th.domain.article.converter;


import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ArticleConverter {

    // 게시글 생성
    // DTO -> 객체
    public static Article toArticle(
        ArticleRequestDTO.CreateArticleDTO dto
    ){
        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    // 객체 -> DTO
    public static ArticleResponseDTO.CreateArticle toCreateArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.CreateArticle.builder()
                .id(article.getId())
                .title(article.getTitle())
                .build();
    }

    // 특정 게시글 조회
    public static ArticleResponseDTO.GetArticle toGetArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.GetArticle.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    // 전체 게시글 조회: JPA
    public static ArticleResponseDTO.GetArticles toGetArticlesDTO(
            Slice<Article> articles,
            String cursor
    ){
        return ArticleResponseDTO.GetArticles.builder()
                .articles(articles.stream().map(ArticleConverter::toGetArticleDTO).toList())
                .cursor(cursor)
                .hasNext(articles.hasNext())
                .build();
    }

    // 전체 게시글 조회: QueryDSL
    public static ArticleResponseDTO.GetArticlesQueryDsl toGetArticlesQueryDSL(
            List<ArticleResponseDTO.GetArticle> articles,
            String cursor,
            Integer pageSize,
            boolean hasNext
    ){
        return ArticleResponseDTO.GetArticlesQueryDsl.builder()
                .articles(articles)
                .cursor(cursor)
                .pageSize(pageSize)
                .hasNext(hasNext)
                .build();
    }

    // 게시글 수정
    public static ArticleResponseDTO.UpdateArticle toUpdateArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.UpdateArticle.builder()
                .id(article.getId())
                .build();
    }

    // 게시글 삭제
    public static ArticleResponseDTO.DeleteArticle toDeleteArticleDTO(
            Article article
    ){
        return ArticleResponseDTO.DeleteArticle.builder()
                .id(article.getId())
                .build();
    }

    // 페이지네이션
    public static <T> ArticleResponseDTO.Page<T> toPageDTO(
            List<T> articles,
            String cursor,
            Integer pageSize,
            boolean hasNext
    ){
        return ArticleResponseDTO.Page.<T>builder()
                .result(articles)
                .cursor(cursor)
                .pageSize(pageSize)
                .hasNext(hasNext)
                .build();
    }

    // 게시글 검색
    public static ArticleResponseDTO.SearchArticle toSearchArticleDTO(
            List<ArticleResponseDTO.GetArticle> articles
    ){
        return ArticleResponseDTO.SearchArticle.builder()
                .articles(articles)
                .build();
    }
}
