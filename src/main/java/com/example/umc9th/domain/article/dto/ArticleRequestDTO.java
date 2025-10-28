package com.example.umc9th.domain.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

//게시글 생성 요청 DTO
public class ArticleRequestDTO {
    @Builder
    public record CreateArticleDTO(
            String title,
            String content
    ) {}

    @Builder
    public record PutDTO (
            @NotBlank(message = "내용이 비어있을 수 없습니다")
            String content
    ){}

    @Builder
    public record PatchDTO (
            String content
    ){}
}
