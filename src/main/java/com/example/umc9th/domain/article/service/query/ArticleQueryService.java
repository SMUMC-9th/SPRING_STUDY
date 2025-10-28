package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    ArticleResponseDTO.ArticleDetailDTO getArticle(Long id);
    List<Article> getArticles();
}
