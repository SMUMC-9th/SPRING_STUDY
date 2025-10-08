package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.reply.dto.response.GetReplyResDTO;
import com.example.umc9th.domain.reply.dto.response.GetReplyWithArticleIdResDTO;
import com.example.umc9th.domain.reply.entity.Reply;

import java.util.List;
import java.util.stream.Collectors;

public class ReplyConverter {

    public static GetReplyResDTO toGetReplyResDTO(Reply reply) {
        return new GetReplyResDTO(
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }
    public static List<GetReplyResDTO> toGetReplyResDTO(List<Reply> replies) {
        return replies.stream()
                .map(ReplyConverter::toGetReplyResDTO)
                .collect(Collectors.toList());
    }

    public static GetReplyWithArticleIdResDTO toGetReplyWithArticleIdResDTO(Reply reply) {
        return new GetReplyWithArticleIdResDTO(
                reply.getArticle().getId(),
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }
    public static List<GetReplyWithArticleIdResDTO> toGetReplyWithArticleIdResDTO(List<Reply> replies) {
        return replies.stream()
                .map(ReplyConverter::toGetReplyWithArticleIdResDTO)
                .collect(Collectors.toList());
    }
}
