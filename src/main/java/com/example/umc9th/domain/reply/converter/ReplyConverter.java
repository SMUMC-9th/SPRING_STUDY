package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;

import java.util.List;

public class ReplyConverter {

    //요청 DTO -> 엔티티
    public static Reply toReply(
            ReplyRequestDTO.CreateReplyDTO dto,
            Article article
    ) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .build();
    }

    //엔티티 -> 응답 DTO
    public static ReplyResponseDTO.ReplyDTO toReplyDTO(
            Reply reply
    ) {
        return ReplyResponseDTO.ReplyDTO.builder()
                .replyId(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .build();
    }

    //엔티티 -> 목록 응답 DTO
    public static ReplyResponseDTO.ReplyListDTO toListDTO(
            List<Reply> replies
    ) {
        return ReplyResponseDTO.ReplyListDTO.builder()
                .replies(replies.stream().map(ReplyConverter::toReplyDTO).toList())
                .build();
    }
}
