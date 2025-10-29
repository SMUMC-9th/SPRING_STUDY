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
    private final ArticleConverter articleConverter;

    @Override
    public ArticleResponseDTO.CreateArticleResponseDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleConverter.toEntity(dto);
        Article savedArticle = articleRepository.save(article);
        return articleConverter.toCreateResponse(savedArticle);
    }

    @Override
    public ArticleResponseDTO.UpdateArticleResponseDTO updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        if (dto.getTitle() != null && !dto.getTitle().isEmpty() && dto.getContent() != null && !dto.getContent().isEmpty()) {
            article.updateAll(dto.getTitle(), dto.getContent());
        }

        return articleConverter.toUpdateResponse(article);
    }

    @Override
    public ArticleResponseDTO.UpdateArticleResponseDTO patchArticle(Long id, ArticleRequestDTO.PatchArticleDTO dto) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            article.updateTitle(dto.getTitle());
        }
        if (dto.getContent() != null && !dto.getContent().isBlank()) {
            article.updateContent(dto.getContent());
        }

        return articleConverter.toUpdateResponse(article);
    }

    @Override
    public ArticleResponseDTO.DeleteArticleResponseDTO deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        article.delete();

        return articleConverter.toDeleteResponse(article);
    }
}