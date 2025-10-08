package com.example.umc9th.domain.article.dto.response;

import com.example.umc9th.domain.reply.dto.response.GetReplyResDTO;

import java.time.LocalDateTime;
import java.util.List;

public record GetArticleWithReplyResDTO(
        Long articleId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int likeNum,
        List<GetReplyResDTO> replyList
) {
}
