package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
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
        summary = "게시글 전체 조회 API"
    )
    public ApiResponse<List<ArticleResponseDTO.GetArticlesResponseDTO>> getArticles() {
        List<ArticleResponseDTO.GetArticlesResponseDTO> response = articleQueryService.getArticles();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}