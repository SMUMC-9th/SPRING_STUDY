package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;

public interface ArticleCommandService {
    ArticleResponse.GetArticleWithReplyResDTO createArticle(ArticleReqDTO dto);
}
