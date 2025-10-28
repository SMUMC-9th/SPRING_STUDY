package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;

import java.util.List;
import java.util.stream.Collectors;

public class ReplyConverter {

    public static Reply toReply(ReplyReqDTO dto, Article article) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .build();
    }

    public static ReplyResponse.GetReplyResDTO toGetReplyResDTO(Reply reply) {
        return new ReplyResponse.GetReplyResDTO(
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }

    public static List<ReplyResponse.GetReplyResDTO> toGetReplyResDTO(List<Reply> replies) {
        return replies.stream()
                .map(ReplyConverter::toGetReplyResDTO)
                .collect(Collectors.toList());
    }

    public static ReplyResponse.GetReplyWithArticleIdResDTO toGetReplyWithArticleIdResDTO(Reply reply) {
        return new ReplyResponse.GetReplyWithArticleIdResDTO(
                reply.getArticle().getId(),
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }

    public static List<ReplyResponse.GetReplyWithArticleIdResDTO> toGetReplyWithArticleIdResDTO(List<Reply> replies) {
        return replies.stream()
                .map(ReplyConverter::toGetReplyWithArticleIdResDTO)
                .collect(Collectors.toList());
    }
}
