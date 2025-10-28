package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

public interface ArticleCommandService {

    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleReqDTO request);
    void deleteArticle(Long id);
}
