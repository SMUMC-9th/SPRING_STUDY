package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.res.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.entity.QArticle;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

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
        /**
         * TODO:
         * 1. 게시글에 댓글이 있는지 확인하는 Query 생성 (ReplyRepository에서)
         * 3. 게시글 Cursor 기반 페이지네이션 {id, 생성 날짜, 좋아요 수} 이 세가지 중 하나로 만들어주세요.
         *     1. 난이도는 id < 생성날짜 < 좋아요 수 입니다.
         *     2. id는 JPA Query Method로도 구현이 가능하지만 좋아요 수는 2주차에 나온 CONCAT을 사용하여 커서를 만들어주어야합니다.
         */

        /**
         * cursor: concat(likeCnt,id): 10자리, String
         * sort: id, createdAt, like
         */

        // 초기화 -> 커서 확인 (있으면 넣고 없으면 안넣고) -> 마지막 결과를 커서로 생성
//        Pageable pageable = PageRequest.of(0, 10);
//        Slice<Article> result;
//        String newCursor;

        // Slice 활용
        // 커서 판단: 서비스 vs 쿼리
        // 테스트 안해봄...
//        if (cursor.equals("-1")){
//            switch (sort) {
//                case "id":
//                case "createdAt":
//                    result = articleRepository.findAllByOrderByIdDesc(pageable);
//                    newCursor = result.getContent().getLast().getId().toString();
//                    break;
//                case "like":
//                    result = articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
//
//                    Article lastResult = result.getContent().getLast();
//                    newCursor = String.format("%010d%010d", lastResult.getLikeNum(), lastResult.getId());
//                    break;
//                default:
//                    throw new ArticleException(ArticleErrorCode.BAD_REQUEST_SORT);
//            }
//        } else {
//            switch (sort) {
//                case "id":
//                case "createdAt":
//                    result = articleRepository.findSliceByOrderById(cursor);
//                    newCursor = result.getContent().getLast().getId().toString();
//                    break;
//                case "like":
//                    result = articleRepository.findSliceByOrderByLikeNum(cursor);
//
//                    Article lastResult = result.getContent().getLast();
//                    newCursor = String.format("%010d%010d", lastResult.getLikeNum(), lastResult.getId());
//                    break;
//                default:
//                    throw new ArticleException(ArticleErrorCode.BAD_REQUEST_SORT);
//            }
//        }
//        return ArticleConverter.toGetArticlesDTO(result, newCursor);

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
        switch (sort) {
            case "id":
            case "createdAt":
                result = articleRepository.findArticlesByCursor(builder, size);

                newCursor = String.format("%010d", result.getLast().id());
                break;
            case "like":
                result = articleRepository.findArticlesByCursor(builder, size);

                newCursor = String.format("%010d%010d", result.getLast().likeNum(), result.getLast().id());
                break;
            default:
                throw new ArticleException(ArticleErrorCode.BAD_REQUEST_SORT);
        }

        // cursor 스타일: 000011000023, 11:23
        // 11:23은 커서 검증에서 후처리

        // 메타데이터 후처리 1/2
        Boolean hasNext = (result.size() > size);

        // 데이터 후처리: 다음 결과를 커서로
        if (hasNext){
            result.removeLast();
//            result = result.subList(0, result.size()-1);
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
