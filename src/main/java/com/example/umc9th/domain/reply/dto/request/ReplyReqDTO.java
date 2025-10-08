package com.example.umc9th.domain.reply.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReplyReqDTO(
        @JsonProperty("article_id")
        Long articleId,

        String content
) {
}
