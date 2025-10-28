package com.example.umc9th.domain.reply.dto.request;

import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class CreateReplyDTO{
        private String title;
        private String content;
    }

    @Getter
    public static class UpdateReplyDTO{
        private String title;
        private String content;
    }
}
