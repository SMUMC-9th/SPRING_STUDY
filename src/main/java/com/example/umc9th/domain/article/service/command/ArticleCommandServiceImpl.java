package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;


    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = ArticleConverter.toArticle(dto);
        // 데이터 베이스에 DTO로 만든 객체 저장하고 저장된 객체 반환
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleReqDTO request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        article.update(request.title(), request.content());
        // 더티 체킹
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.delete(article);
    }
}
