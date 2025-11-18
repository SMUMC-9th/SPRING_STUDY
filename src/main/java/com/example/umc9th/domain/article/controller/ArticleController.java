package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.ArticleResponseDTO;
import com.example.umc9th.domain.article.service.ArticleService;
import com.example.umc9th.global.apiPayload.GlobalResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> createArticle(
            @RequestBody ArticleRequestDTO.CreateArticleDTO request
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.createArticle(request);
        return GlobalResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    @GetMapping("/articles/{articleId}")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> getArticle(
            @PathVariable("articleId") Long articleId
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.getArticle(articleId);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping("/articles")
    public GlobalResponse<ArticleResponseDTO.ArticleListDTO> getArticles() {
        ArticleResponseDTO.ArticleListDTO response = articleService.getArticles();
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //전체 수정
    @PutMapping("/article/{id}")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> put(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO.PutDTO dto
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.putArticle(id, dto);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //부분 수정
    @PatchMapping("/article/{id}")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> patch(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO.PatchDTO dto
    ) {
        ArticleResponseDTO.ArticleDTO response = articleService.patchArticle(id, dto);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //삭제
    @DeleteMapping("/article/{id}/delete")
    public GlobalResponse<Long> delete(
            @PathVariable Long id
    ) {
        Long deletedId = articleService.deleteArticle(id);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, deletedId);
    }

    //좋아요 수 기반 커서 페이지네이션
    @GetMapping("/posts/articles")
    public GlobalResponse<ArticleResponseDTO.ArticleCursorPageDTO> getArticlesByCursor(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        ArticleResponseDTO.ArticleCursorPageDTO response = articleService.getArticlesByCursor(cursor, size);
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}