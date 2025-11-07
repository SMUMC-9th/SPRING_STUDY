package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.QArticle;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleQueryDSLImpl implements ArticleQueryDSL{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleResponseDTO.GetArticle> findArticlesByCursor(
            Predicate query,
            Integer size
    ){

        // Q클래스 정의
        QArticle article = QArticle.article;

        return queryFactory
                .from(article)
                .where(query)
                .limit(size+1)
                .orderBy(article.likeNum.desc(), article.id.desc())
                .transform(GroupBy.groupBy(article.id)
                        .list(
                                Projections.constructor(
                                        ArticleResponseDTO.GetArticle.class,
                                        article.id,
                                        article.title,
                                        article.content,
                                        article.likeNum
                                )
                        ));
    }

    // 게시글 검색
    @Override
    public List<ArticleResponseDTO.GetArticle> searchArticle(
            Predicate query
    ){
        QArticle article = QArticle.article;

        return queryFactory
                .from(article)
                .where(query)
                .transform(GroupBy.groupBy(article.id)
                        .list(
                                Projections.constructor(
                                        ArticleResponseDTO.GetArticle.class,
                                        article.id,
                                        article.title,
                                        article.content,
                                        article.likeNum
                                )
                        ));
    }
}
