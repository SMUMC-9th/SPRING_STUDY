package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.code.ArticleSuccessCode;
import com.example.umc9th.domain.article.service.command.ArticleCommandService;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 게시글 생성
    @PostMapping("/articles")
    public ApiResponse<ArticleResponseDTO.CreateArticle> createArticle(
            @RequestBody ArticleRequestDTO.CreateArticleDTO dto
    ) {

        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);

        // 성공 코드 생성
        ArticleSuccessCode code = ArticleSuccessCode.CREATE;

        // 응답 통일
        return ApiResponse.onSuccess(code, ArticleConverter.toCreateArticleDTO(article));
    }

    // 특정 게시글 조회
    @GetMapping("/articles/{articleId}")
    public ApiResponse<ArticleResponseDTO.GetArticle> getArticle(
            @PathVariable("articleId") Long articleId
    ) {

        // 특정 게시글 조회
        Article article = articleQueryService.getArticle(articleId);

        // 성공 코드 생성
        ArticleSuccessCode code = ArticleSuccessCode.FOUND;

        return ApiResponse.onSuccess(code, ArticleConverter.toGetArticleDTO(article));
    }

    // 게시글 수정
    @PatchMapping("/articles/{articleId}")
    public ApiResponse<ArticleResponseDTO.UpdateArticle> updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto
    ){
        Optional<ArticleResponseDTO.UpdateArticle> article = articleCommandService.updateArticle(articleId, dto);
        if (article.isPresent()){
            ArticleSuccessCode code = ArticleSuccessCode.PATCH_SUCCESS;
            return ApiResponse.onSuccess(code, article.get());
        } else {
            ArticleSuccessCode code = ArticleSuccessCode.NO_CONTENT;
            return ApiResponse.onSuccess(code, null);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/articles/{articleId}")
    public ApiResponse<ArticleResponseDTO.DeleteArticle> deleteArticle(
            @PathVariable("articleId") Long articleId
    ){
        ArticleSuccessCode code = ArticleSuccessCode.DELETE;
        return ApiResponse.onSuccess(code, articleCommandService.deleteArticle(articleId));
    }

    // 모든 게시글 조회: 게시글 cursor, sort
    @GetMapping("/articles")
    public ApiResponse<ArticleResponseDTO.GetArticlesQueryDsl> getArticles(
            @RequestParam(value = "cursor", required = false, defaultValue = "-1") String cursor,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "id") String sort
    ){
        return ApiResponse.onSuccess(ArticleSuccessCode.FOUND, articleQueryService.getArticles(cursor, size, sort));
    }

    // 제목 검색
    @GetMapping("/articles/search")
    public ApiResponse<ArticleResponseDTO.SearchArticle> searchArticles(
            @RequestParam(value = "query") String query
    ){
        return ApiResponse.onSuccess(ArticleSuccessCode.FOUND, articleQueryService.searchArticle(query));
    }
}