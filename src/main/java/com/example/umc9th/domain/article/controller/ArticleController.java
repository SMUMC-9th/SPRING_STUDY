package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.service.command.ArticleCommandService;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping
    @Operation(
        summary = "게시글 생성 API"
    )
    public ApiResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        ArticleResponseDTO.CreateArticleResponseDTO response = articleCommandService.createArticle(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    @GetMapping("/{articleId}")
    @Operation(
        summary = "게시글 하나 조회 조회 API"
    )
    public ApiResponse<ArticleResponseDTO.GetArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDTO.GetArticleResponseDTO response = articleQueryService.getArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping
    @Operation(
        summary = "게시글 전체 조회 API - 검색, Cursor 페이지네이션 지원"
    )
    public ApiResponse<ArticleResponseDTO.ArticleCursorResponseDTO> getArticles(
        @RequestParam(required = false) String cursor,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false, defaultValue = "id") String sort,
        @RequestParam(required = false) String keyword) {

        ArticleResponseDTO.ArticleCursorResponseDTO response = articleQueryService.searchArticlesByTitle(keyword, cursor, size, sort);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @PutMapping("/{articleId}")
    @Operation(
        summary = "게시글 전체 수정 API (PUT) - title과 content 모두 필수"
    )
    public ApiResponse<ArticleResponseDTO.UpdateArticleResponseDTO> updateArticle(
        @PathVariable("articleId") Long articleId,
        @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        ArticleResponseDTO.UpdateArticleResponseDTO response = articleCommandService.updateArticle(articleId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @PatchMapping("/{articleId}")
    @Operation(
        summary = "게시글 부분 수정 API (PATCH) - title 또는 content 선택"
    )
    public ApiResponse<ArticleResponseDTO.UpdateArticleResponseDTO> patchArticle(
        @PathVariable("articleId") Long articleId,
        @RequestBody ArticleRequestDTO.PatchArticleDTO dto) {
        ArticleResponseDTO.UpdateArticleResponseDTO response = articleCommandService.patchArticle(articleId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @DeleteMapping("/{articleId}")
    @Operation(
        summary = "게시글 삭제 API (Soft Delete)"
    )
    public ApiResponse<ArticleResponseDTO.DeleteArticleResponseDTO> deleteArticle(
        @PathVariable("articleId") Long articleId) {
        ArticleResponseDTO.DeleteArticleResponseDTO response = articleCommandService.deleteArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}