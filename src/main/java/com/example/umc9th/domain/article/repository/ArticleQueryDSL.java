package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ArticleQueryDSL {
    List<ArticleResponseDTO.GetArticle> findArticlesByCursor(
            Predicate query,
            Integer size
    );

    // 게시글 검색
    List<ArticleResponseDTO.GetArticle> searchArticle(
            Predicate query
    );
}
