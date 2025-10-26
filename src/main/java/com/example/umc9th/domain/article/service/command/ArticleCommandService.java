package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.Optional;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);

    // 게시글 수정
    Optional<ArticleResponseDTO.UpdateArticle> updateArticle(
            Long articleId,
            ArticleRequestDTO.UpdateArticleDTO dto
    );
}