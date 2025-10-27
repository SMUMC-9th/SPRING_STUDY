package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.ArticleResponseDTO;
import com.example.umc9th.domain.article.service.ArticleService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public ApiResponse<ArticleResponseDTO.ArticleDTO> createArticle(
            @RequestBody ArticleRequestDTO.CreateArticleDTO request
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.createArticle(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    @GetMapping("/articles/{articleId}")
    public ApiResponse<ArticleResponseDTO.ArticleDTO> getArticle(
            @PathVariable("articleId") Long articleId
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.getArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping("/articles")
    public ApiResponse<ArticleResponseDTO.ArticleListDTO> getArticles() {
        ArticleResponseDTO.ArticleListDTO response = articleService.getArticles();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}