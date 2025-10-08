package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;

import java.util.List;

public interface ArticleQueryService {
    ArticleResponseDTO.ArticleDTO getArticle(Long id);
    List<ArticleResponseDTO.ArticleDTO> getArticles();
}