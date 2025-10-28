package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.stereotype.Component;

@Component //static 말고 DI방식으로 해보기
public class ReplyConverter {

    public Reply toEntity(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .build();
    }

    public ReplyResponseDTO.ReplyResDTO toDTO(Reply reply) {
        return ReplyResponseDTO.ReplyResDTO.builder()
                .replyId(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }
}
