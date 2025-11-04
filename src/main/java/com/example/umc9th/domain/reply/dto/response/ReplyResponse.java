package com.example.umc9th.domain.reply.dto.response;

import com.example.umc9th.domain.reply.entity.Reply;

public record ReplyResponse(
        String content
) {
    public static ReplyResponse from(Reply reply){
        return new ReplyResponse(
                reply.getContent()
        );
    }
}
