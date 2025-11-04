package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;


    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(
                // Builder 패턴 사용
                Article.builder()
                        .content(dto.getContent())
                        .build()
        );
    }

    @Override
    public Article updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto) {
        // 게시글 조회 (없으면 예외 발생)
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        articleRepository.delete(article);
    }
}
