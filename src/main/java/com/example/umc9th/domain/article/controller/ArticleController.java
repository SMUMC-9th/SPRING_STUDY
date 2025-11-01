package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.service.command.ArticleCommandService;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="ARTICLE API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("")
    @Operation(method = "POST", summary = "Article 작성 API", description = "Article을 작성합니다.")
    public ApiResponse<ArticleResponse.GetArticleWithReplyResDTO> createArticle(@RequestBody ArticleReqDTO dto) {
        ArticleResponse.GetArticleWithReplyResDTO article = articleCommandService.createArticle(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED_201, article);
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "특정 Article 조회 API", description = "특정 Article을 조회합니다.")
    public ApiResponse<ArticleResponse.GetArticleWithReplyResDTO> getArticle(
            @Parameter(description = "조회할 Article Id")
            @PathVariable("articleId") Long articleId
    ) {
        ArticleResponse.GetArticleWithReplyResDTO article = articleQueryService.getArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, article);
    }

    @GetMapping("")
    @Operation(method = "GET", summary = "Article 전체 조회 API", description = "커서 기반으로 Article을 조회합니다.")
    public ApiResponse<ArticleResponse.GetArticleWithCursorResDTO> getArticleList(
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        ArticleResponse.GetArticleWithCursorResDTO articles = articleQueryService.getArticlesList(cursor, limit);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, articles);
    }

    @PutMapping("/update/{article_id}")
    @Operation(method = "PUT", summary = "특정 Article 전체 수정 API", description = "특정 Article을 전체 수정합니다.")
    public ApiResponse<ArticleResponse.GetArticleWithReplyResDTO> updateArticle(
            @Parameter(description = "수정할 Article Id")
            @PathVariable("article_id") Long articleId,
            @RequestBody ArticleReqDTO dto
    ) {
        ArticleResponse.GetArticleWithReplyResDTO article = articleCommandService.updateArticle(articleId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, article);
    }

    @PatchMapping("/patch/{articleId}")
    @Operation(method = "PATCH", summary = "특정 Article 수정 API", description = "특정 Article을 수정합니다.")
    public ApiResponse<ArticleResponse.GetArticleWithReplyResDTO> patchArticle(
            @Parameter(description = "수정할 Article Id")
            @PathVariable("articleId") Long articleId,
            @RequestBody ArticleReqDTO dto
    ) {
        ArticleResponse.GetArticleWithReplyResDTO article = articleCommandService.patchArticle(articleId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, article);
    }

    @DeleteMapping("/delete/{articleId}")
    @Operation(method = "DELETE", summary = "특정 Article 삭제 API", description = "특정 Article을 삭제합니다.")
    public ApiResponse<Void> deleteArticle(
            @Parameter(description = "삭제할 Article Id")
            @PathVariable("articleId") Long articleId
    ){
        articleCommandService.deleteArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.NO_CONTENT_204, null);
    }
}
