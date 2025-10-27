package com.example.umc9th.domain.article.dto;

import lombok.Builder;
import java.util.List;
public class ArticleResponseDTO {

    //단건 응답 DTO
    @Builder
    public record ArticleDTO(
            Long articleId,
            String title,
            String content,
            Integer likeNum
    ){}

    //목록 요약 DTO
    @Builder
    public record ArticleSummaryDTO(
            Long articleId,
            String title,
            Integer likeNum
    ) {}

    //목록 응답 DTO
    @Builder
    public record ArticleListDTO(
            List<ArticleSummaryDTO> articles
    ) {}
}
