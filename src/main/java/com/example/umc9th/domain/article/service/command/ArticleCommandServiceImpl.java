package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponse.GetArticleWithReplyResDTO createArticle(ArticleReqDTO dto) {
        Article article = ArticleConverter.toArticle(dto);

        Article saved = articleRepository.save(article);

        return ArticleConverter.toGetArticleWithReplyResDTO(saved);
    }

    @Override
    public ArticleResponse.GetArticleWithReplyResDTO updateArticle(Long articleId, ArticleReqDTO dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        article.updateTitle(dto.title());
        article.updateContent(dto.content());

        return ArticleConverter.toGetArticleWithReplyResDTO(article);
    }

    @Override
    public ArticleResponse.GetArticleWithReplyResDTO patchArticle(Long articleId, ArticleReqDTO dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        if(dto.title() != null && !dto.title().isBlank()) {
            article.updateTitle(dto.title());
        }

        if(dto.content() != null && !dto.content().isBlank()) {
            article.updateContent(dto.content());
        }
        
        return ArticleConverter.toGetArticleWithReplyResDTO(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.delete(article);
    }
}
