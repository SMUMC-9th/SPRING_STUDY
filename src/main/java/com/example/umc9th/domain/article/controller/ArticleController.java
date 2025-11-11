package com.example.umc9th.domain.article.controller;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.service.command.ArticleCommandService;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Article", description = "게시글 관련 API")
// RestController 명시
@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;


    @Operation(summary = "게시글 생성", description = "제목과 내용을 입력하여 게시글을 생성합니다.")
    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    public ApiResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED,article);
    }


    @Operation(summary = "단일 게시글 조회", description = "게시글 ID로 상세 조회합니다.")
    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public ApiResponse<ArticleResponseDTO.ArticleDetailDTO> getArticle(@PathVariable("articleId") Long articleId) {

        //맛보기로 이것만 반환dto로 했습니다.
        ArticleResponseDTO.ArticleDetailDTO response = articleQueryService.getArticle(articleId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,response);
    }

    @Operation(summary = "좋아요순 게시글 조회", description = "좋아요순으로 게시글 목록을 조회합니다.")
    @GetMapping("/articles/like")
    public ApiResponse<ArticleResponseDTO.ArticleCursorDTO> getArticlesByLikeCursor(
            @RequestParam(required = false) String Lastcursor,
            @RequestParam(defaultValue = "10") int limit
    ) {
        ArticleResponseDTO.ArticleCursorDTO response = articleQueryService.getArticlesByLikeCursor(Lastcursor,limit);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @Operation(summary = "전체 게시글 조회", description = "전체 게시글 목록을 조회합니다.")
    @GetMapping("/articles")
    public ApiResponse<List<Article>> getArticles() {

        List<Article> articleList = articleQueryService.getArticles();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,articleList);
    }


    @Operation(summary = "게시글 수정", description = "게시글 ID로 제목/내용을 수정합니다.")
    @PatchMapping("/articles/{articleId}")
    public ApiResponse<ArticleResponseDTO.UpdateArticleResDTO> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleReqDTO request
    ) {
        Article updatedArticle = articleCommandService.updateArticle(articleId, request);
        // Converter를 사용하여 DTO 변환
        ArticleResponseDTO.UpdateArticleResDTO response = ArticleConverter.toUpdateResDTO(updatedArticle);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @Operation(summary = "게시글 삭제", description = "게시글 ID로 게시글을 삭제합니다.")
    @DeleteMapping("/articles/{articleId}")
    public ApiResponse<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }


}

