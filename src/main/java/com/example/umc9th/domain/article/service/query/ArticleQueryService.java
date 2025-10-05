package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {

    Article getArticle(Long id);
    List<Article> getArticles();
}