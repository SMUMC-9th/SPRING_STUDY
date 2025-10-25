package com.example.umc9th.domain.reply.dto.res;

import lombok.Builder;

import java.util.List;

public class ReplyResponseDTO {

    // 댓글 생성
    @Builder
    public record CreateReply(
            Long id,
            String content
    ){}

    // 특정 댓글 조회
    @Builder
    public record GetReply(
            Long id,
            String content
    ){}

    // 전체 댓글 조회
    @Builder
    public record GetReplies(
            List<GetReply> replies
    ){}
}
