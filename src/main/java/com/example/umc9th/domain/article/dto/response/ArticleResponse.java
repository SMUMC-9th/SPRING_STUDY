package com.example.umc9th.domain.article.dto.response;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponse {

    public record GetArticleResDTO(
            Long articleId,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            int likeNum
    ) {
    }

    public record GetArticleWithCursorResDTO(
            List<GetArticleResDTO> articleList,
            boolean hasNext,
            String cursor
    ) {
    }

    public record GetArticleWithReplyResDTO(
            Long articleId,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            int likeNum,
            List<ReplyResponse.GetReplyResDTO> replyList
    ) {
    }
}
