package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.ArticleResponseDTO;
import com.example.umc9th.domain.article.service.ArticleService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
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

    //전체 수정
    @PutMapping("/article/{id}")
    public ApiResponse<ArticleResponseDTO.ArticleDTO> put(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO.PutDTO dto
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.putArticle(id, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //부분 수정
    @PatchMapping("/article/{id}")
    public ApiResponse<ArticleResponseDTO.ArticleDTO> patch(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO.PatchDTO dto
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.patchArticle(id, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}