package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;

public interface ArticleCommandService {
    ArticleResponse.GetArticleWithReplyResDTO createArticle(ArticleReqDTO dto);
    ArticleResponse.GetArticleWithReplyResDTO updateArticle(Long articleId, ArticleReqDTO dto);
    ArticleResponse.GetArticleWithReplyResDTO patchArticle(Long articleId, ArticleReqDTO dto);
    void deleteArticle(Long articleId);
}
