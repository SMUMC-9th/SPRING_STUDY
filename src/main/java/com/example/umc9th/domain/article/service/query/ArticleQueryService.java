package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleListResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    // cursor 기반 페이지네이션
    ArticleListResponseDTO getArticles(Long cursorId, int size);

    Article getArticle(Long id);
}
