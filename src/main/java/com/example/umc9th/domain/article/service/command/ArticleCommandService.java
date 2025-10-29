package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;

public interface ArticleCommandService {
    ArticleResponseDTO.CreateArticleResponseDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    ArticleResponseDTO.UpdateArticleResponseDTO updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    ArticleResponseDTO.UpdateArticleResponseDTO patchArticle(Long id, ArticleRequestDTO.PatchArticleDTO dto);
    ArticleResponseDTO.DeleteArticleResponseDTO deleteArticle(Long id);
}