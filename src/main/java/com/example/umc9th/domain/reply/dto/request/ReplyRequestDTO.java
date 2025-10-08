package com.example.umc9th.domain.reply.dto.request;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.entity.Reply;
import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private String content;
        private Long articleId;

        public Reply toEntity(Article article) {
            return Reply.builder()
                    .content(this.content)
                    .article(article)
                    .build();
        }
    }
}
