package com.example.umc9th.domain.article.service.command;


import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    // 게시글 생성
    @Override
    public Article createArticle(
            ArticleRequestDTO.CreateArticleDTO dto
    ) {

        // 객체 생성
        Article article = ArticleConverter.toArticle(dto);

        // save
        return articleRepository.save(article);
    }

    // 게시글 수정
    @Override
    public Optional<ArticleResponseDTO.UpdateArticle> updateArticle(
            Long articleId,
            ArticleRequestDTO.UpdateArticleDTO dto
    ){

        // 플래그 설정
        boolean isUpdated = false;

        // 존재여부 확인
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        // 수정: 있을때만 수정
        if (dto.title() != null){
            article.updateTitle(dto.title());
            isUpdated = true;
        }
        if (dto.content() != null){
            article.updateContent(dto.content());
            isUpdated = true;
        }

        // 수정 여부 확인
        if (isUpdated){
            return Optional.of(ArticleConverter.toUpdateArticleDTO(article));
        } else {
            return Optional.empty();
        }
    }

    // 게시글 삭제 (Soft Delete)
    @Override
    public ArticleResponseDTO.DeleteArticle deleteArticle(
            Long articleId
    ){

        // 존재 여부 확인
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        // 삭제 처리: JPA Delete
        articleRepository.delete(article);

        // 삭제처리: 메서드
        // articleRepository.softDelete(articleId);

        return ArticleConverter.toDeleteArticleDTO(article);
    }
}
