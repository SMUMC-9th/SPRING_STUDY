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

    //게시글 생성
    @Transactional
    public ArticleResponseDTO.ArticleDTO createArticle(
            ArticleRequestDTO.CreateArticleDTO dto
    ) {
        Article article = ArticleConverter.toArticle(dto);
        Article saved = articleRepository.save(article);
        return ArticleConverter.toArticleDTO(saved);
    }

    //게시글 조회
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleDTO getArticle(
            Long id
    ) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        return ArticleConverter.toArticleDTO(article);
    }

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleListDTO getArticles(

    ) {
        List<Article> all = articleRepository.findAll();
        return ArticleConverter.toListDTO(all);
    }

    //전체 수정
    @Transactional
    public ArticleResponseDTO.ArticleDTO putArticle(
            Long id,
            ArticleRequestDTO.PutDTO dto
    ) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        article.update(dto.content());
        return ArticleConverter.toArticleDTO(article);

    }

    //부분 수정
    @Transactional
    public ArticleResponseDTO.ArticleDTO patchArticle(
            Long id,
            ArticleRequestDTO.PatchDTO dto
    ) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        article.patch(dto.content());
        return ArticleConverter.toArticleDTO(article);

    }

    //삭제
    @Transactional
    public Long deleteArticle(
            Long id
    ){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404)
        );
        articleRepository.delete(article);
        return id;
    }
}
