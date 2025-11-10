package com.example.umc9th.domain.article.dto.response;

import com.example.umc9th.domain.article.entity.Article;

import java.util.List;

public record ArticleListResponseDTO(
        List<ArticleResponseDTO> articles,
        Long nextCursorId,
        boolean hasNext
) {
}
