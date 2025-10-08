package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;
import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    GetArticleResDTO getArticle(Long id);
    List<GetArticleResDTO> getArticlesList();
}
