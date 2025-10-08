package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.umc9th.domain.article.converter.ArticleConverter.toGetArticleResDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public GetArticleResDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return toGetArticleResDTO(article);
    }

    @Override
    public List<GetArticleResDTO> getArticlesList() {
        List<Article> articleList = articleRepository.findAll();
        return toGetArticleResDTO(articleList);
    }
}
