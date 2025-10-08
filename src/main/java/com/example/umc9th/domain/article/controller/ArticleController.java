package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;
import com.example.umc9th.domain.article.service.command.ArticleCommandService;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("")
    @Operation(method = "POST", summary = "Article 작성 API", description = "Article을 작성합니다.")
    public ApiResponse<GetArticleResDTO> createArticle(@RequestBody ArticleReqDTO dto) {
        GetArticleResDTO article = articleCommandService.createArticle(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED_201, article);
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "특정 Article 조회 API", description = "특정 Article을 조회합니다.")
    public ApiResponse<GetArticleResDTO> getArticle(
            @Parameter(description = "조회할 Article Id")
            @PathVariable("articleId") Long articleId
    ) {
        GetArticleResDTO article = articleQueryService.getArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, article);
    }

    @GetMapping("")
    @Operation(method = "GET", summary = "Article 전체 조회 API", description = "모든 Article을 조회합니다.")
    public ApiResponse<List<GetArticleResDTO>> getArticleList() {
        List<GetArticleResDTO> articles = articleQueryService.getArticlesList();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, articles);
    }
}
