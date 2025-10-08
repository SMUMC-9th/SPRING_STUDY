package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.response.ReplyResDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    public static GetArticleResDTO toGetArticleResDTO(Article article) {
        List<ReplyResDTO> replyList = article.getReplyList().stream()
                .map(reply -> new ReplyResDTO(
                        reply.getId(),
                        reply.getContent(),
                        reply.getCreatedAt(),
                        reply.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return new GetArticleResDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getLikeNum(),
                replyList
        );
    }

    public static List<GetArticleResDTO> toGetArticleResDTO(
            List<Article> articles
    ) {
        return articles.stream()
                .map(ArticleConverter::toGetArticleResDTO)
                .collect(Collectors.toList());
    }
}
