package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;

public interface ArticleCommandService {
    GetArticleResDTO createArticle(ArticleReqDTO dto);
}
