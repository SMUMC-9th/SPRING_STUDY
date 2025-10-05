package com.example.umc9th.domain.article.service;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(
                Article.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build());
    }

    @Transactional
    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

    @Transactional
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
