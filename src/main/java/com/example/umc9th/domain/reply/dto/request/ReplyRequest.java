package com.example.umc9th.domain.reply.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyRequest {

    public record ReplyReqDTO(
            @JsonProperty("article_id")
            Long articleId,

            String content
    ) {
    }

    public record ReplyPatchReqDTO(
            String content
    ) {

    }
}
