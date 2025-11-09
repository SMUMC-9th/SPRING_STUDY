package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponse.GetArticleWithReplyResDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return ArticleConverter.toGetArticleWithReplyResDTO(article);
    }

    @Override
    public ArticleResponse.GetArticleWithCursorResDTO getArticlesList(String cursor, int limit) {

        Slice<Article> articleList = articleRepository.findByCursor(cursor, limit);

        boolean hasNext = articleList.hasNext();

        String nextCursor = null;

        if (!articleList.isEmpty()) {
            Article last = articleList.getContent().getLast();
            nextCursor = String.format("%010d%010d", last.getLikeNum(), last.getId());
        }

        return ArticleConverter.toGetArticleWithCursorResDTO(articleList.getContent(), hasNext, nextCursor);
    }
}
