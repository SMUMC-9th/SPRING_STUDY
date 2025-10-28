package com.example.umc9th.domain.reply.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class ReplyRequestDTO {

    //요청 DTO
    @Builder
    public record CreateReplyDTO (
            String content
    ){}

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
