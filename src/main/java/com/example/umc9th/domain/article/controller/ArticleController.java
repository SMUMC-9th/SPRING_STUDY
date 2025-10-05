package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.service.ArticleService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public ApiResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleService.createArticle(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, article);
    }

    @GetMapping("/articles/{articleId}")
    public ApiResponse<Article> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleService.getArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, article);
    }

    @GetMapping("/articles")
    public ApiResponse<List<Article>> getArticles() {
        List<Article> articles = articleService.getArticles();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, articles);
    }
}