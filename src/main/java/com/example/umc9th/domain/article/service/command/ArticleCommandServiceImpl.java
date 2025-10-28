package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Override
    public ArticleResponseDTO.CreateArticleResponseDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleConverter.toEntity(dto);
        Article savedArticle = articleRepository.save(article);
        return ArticleResponseDTO.CreateArticleResponseDTO.from(savedArticle);
    }
}