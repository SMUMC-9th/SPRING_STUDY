package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Override
    public ArticleResponseDTO.GetArticleResponseDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return articleConverter.toGetResponse(article);
    }

    @Override
    public List<ArticleResponseDTO.GetArticlesResponseDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articleConverter.toGetListResponse(articles);
    }
}