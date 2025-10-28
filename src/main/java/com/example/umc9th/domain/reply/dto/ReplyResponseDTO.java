package com.example.umc9th.domain.reply.dto;

import lombok.Builder;

import java.util.List;

public class ReplyResponseDTO {

    //응답 DTO
    @Builder
    public record ReplyDTO(
            Long replyId,
            Long articleId,
            String content
    ) {}

    //목록 DTO
    @Builder
    public record ReplyListDTO(
            List<ReplyDTO> replies
    ) {}



}
