package com.example.umc9th.domain.reply.dto;

import lombok.Builder;

public class ReplyRequestDTO {

    //요청 DTO
    @Builder
    public record CreateReplyDTO (
            String content
    ){}
}
