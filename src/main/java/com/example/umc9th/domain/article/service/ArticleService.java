package com.example.umc9th.domain.article.service;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.ArticleResponseDTO;
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
    public ArticleResponseDTO.ArticleDTO createArticle(
            ArticleRequestDTO.CreateArticleDTO dto
    ) {
        Article article = ArticleConverter.toArticle(dto);
        Article saved = articleRepository.save(article);
        return ArticleConverter.toArticleDTO(saved);
    }

    @Transactional
    public ArticleResponseDTO.ArticleDTO getArticle(
            Long id
    ) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        return ArticleConverter.toArticleDTO(article);
    }

    @Transactional
    public ArticleResponseDTO.ArticleListDTO getArticles(

    ) {
        List<Article> all = articleRepository.findAll();
        return ArticleConverter.toListDTO(all);
    }
}
