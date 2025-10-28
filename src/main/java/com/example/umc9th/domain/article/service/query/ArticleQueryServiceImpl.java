package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<ArticleResponse.GetArticleResDTO> getArticlesList() {
        List<Article> articleList = articleRepository.findAll();
        return ArticleConverter.toGetArticleResDTO(articleList);
    }
}
