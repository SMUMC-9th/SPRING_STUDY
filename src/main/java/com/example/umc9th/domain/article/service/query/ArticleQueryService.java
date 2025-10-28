package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleResponse;

import java.util.List;

public interface ArticleQueryService {
    ArticleResponse.GetArticleWithReplyResDTO getArticle(Long id);
    List<ArticleResponse.GetArticleResDTO> getArticlesList();
}
