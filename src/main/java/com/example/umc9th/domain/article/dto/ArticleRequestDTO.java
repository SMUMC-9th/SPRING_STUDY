package com.example.umc9th.domain.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class ArticleRequestDTO {

    //요청 DTO
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
