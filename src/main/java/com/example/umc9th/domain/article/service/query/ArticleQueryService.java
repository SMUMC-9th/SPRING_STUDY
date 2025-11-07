package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

public interface ArticleQueryService {

    Article getArticle(Long id);

    // 게시글 조회
    ArticleResponseDTO.GetArticlesQueryDsl getArticles(
            String cursor,
            Integer size,
            String sort
    );

    // 게시글 검색
    ArticleResponseDTO.SearchArticle searchArticle(String query);
}