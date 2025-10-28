package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyConverter {

    public Reply toEntity(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
            .content(dto.getContent())
            .article(article)
            .build();
    }
}
