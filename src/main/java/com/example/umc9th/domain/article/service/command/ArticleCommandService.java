package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleReqDTO dto);
}
