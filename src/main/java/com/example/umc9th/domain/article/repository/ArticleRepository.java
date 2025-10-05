package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
