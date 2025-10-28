package com.example.umc9th.domain.article.converter;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    public static Article toArticle(ArticleReqDTO dto) {
        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public static ArticleResponse.GetArticleWithReplyResDTO toGetArticleWithReplyResDTO(Article article) {
        List<ReplyResponse.GetReplyResDTO> replyList = article.getReplyList().stream()
                .map(ReplyConverter::toGetReplyResDTO)
                .collect(Collectors.toList());

        return new ArticleResponse.GetArticleWithReplyResDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getLikeNum(),
                replyList
        );
    }

    public static ArticleResponse.GetArticleResDTO toGetArticleResDTO(Article article) {
        return new ArticleResponse.GetArticleResDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getLikeNum()
        );
    }

    public static List<ArticleResponse.GetArticleResDTO> toGetArticleResDTO(
            List<Article> articles
    ) {
        return articles.stream()
                .map(ArticleConverter::toGetArticleResDTO)
                .collect(Collectors.toList());
    }
}
