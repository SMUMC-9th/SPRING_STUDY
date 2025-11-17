package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.entity.QArticle;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public Article getArticle(
            Long id
    ) {

        // 조회
        Article article = articleRepository.findById(id).orElse(null);

        // 없는 경우
        if (article == null){
            throw new ArticleException(ArticleErrorCode.NOT_FOUND);
        }

        return article;
    }

    // 게시글 조회
    @Override
    public ArticleResponseDTO.GetArticlesQueryDsl getArticles(
            String cursor,
            Integer size,
            String sort
    ){
        // QueryDSL
        // Q클래스 정의
        QArticle article = QArticle.article;

        BooleanBuilder builder = new BooleanBuilder();

        if (!cursor.equals("-1") && !sort.equals("like")){
            Long id = Long.parseLong(cursor.substring(0, 10));

            builder.and(article.id.goe(id));
        } else if (!cursor.equals("-1")){
            // 커서 분리
            Integer likeNum = Integer.parseInt(cursor.substring(0, 10));
            Long id = Long.parseLong(cursor.substring(11, 20));

            // 조건 설정: likeNum < :likeNum or likeNum = :likeNum and id <= :id
            builder.and(article.likeNum.lt(likeNum));
            builder.or(article.likeNum.eq(likeNum).and(article.id.loe(id)));
        }

        String newCursor;
        List<ArticleResponseDTO.GetArticle> result;
        newCursor = switch (sort) {
            case "id", "createdAt" -> {
                result = articleRepository.findArticlesByCursor(builder, size);

                yield String.format("%010d", result.getLast().id());
            }
            case "like" -> {
                result = articleRepository.findArticlesByCursor(builder, size);

                yield String.format("%010d%010d", result.getLast().likeNum(), result.getLast().id());
            }
            default -> throw new ArticleException(ArticleErrorCode.BAD_REQUEST_SORT);
        };

        // cursor 스타일: 000011000023, 11:23
        // 11:23은 커서 검증에서 후처리

        // 메타데이터 후처리 1/2
        boolean hasNext = (result.size() > size);

        // 데이터 후처리: 다음 결과를 커서로
        if (hasNext){
            result.removeLast();
        }

        // 메타데이터 후처리 2/2
        Integer pageSize = result.size();

        return ArticleConverter.toGetArticlesQueryDSL(result, newCursor, pageSize, hasNext);
    }

    // 게시글 검색
    @Override
    public ArticleResponseDTO.SearchArticle searchArticle(
            String query
    ){
        QArticle article = QArticle.article;

        BooleanBuilder builder = new BooleanBuilder();
        String lowerQuery = "%"+query.toLowerCase()+"%";
        builder.and(article.title.like(lowerQuery));

        List<ArticleResponseDTO.GetArticle> result = articleRepository.searchArticle(builder);

        return ArticleConverter.toSearchArticleDTO(result);
    }
}
