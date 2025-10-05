package com.example.umc9th.domain.reply.converter;


import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.res.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;

import java.util.List;

public class ReplyConverter {

    // 게시글 생성
    // DTO -> 객체
    public static Reply toReply(
        ReplyRequestDTO.CreateReply dto
    ){
        return Reply.builder()
                .content(dto.content())
                .build();
    }

    // 객체 -> DTO
    public static ReplyResponseDTO.CreateReply toCreateReply(
            Reply reply
    ){
        return ReplyResponseDTO.CreateReply.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .build();
    }

    // 특정 댓글 조회
    // 객체 -> DTO
    public static ReplyResponseDTO.GetReply toGetReply(
            Reply reply
    ){
        return ReplyResponseDTO.GetReply.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .build();
    }

    // 전체 댓글 조회
    // 객체 -> DTO
    public static ReplyResponseDTO.GetReplies toGetReplies(
        List<Reply> replies
    ){
        return ReplyResponseDTO.GetReplies.builder()
                .replies(replies.stream().map(ReplyConverter::toGetReply).toList())
                .build();
    }
}
