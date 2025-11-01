package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleResponse;

public interface ArticleQueryService {
    ArticleResponse.GetArticleWithReplyResDTO getArticle(Long id);
    ArticleResponse.GetArticleWithCursorResDTO getArticlesList(String cursor, int limit);
}
