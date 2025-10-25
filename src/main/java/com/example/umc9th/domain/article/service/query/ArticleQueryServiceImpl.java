package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
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

    @Override
    public List<Article> getArticles(

    ){

        // 전체 조회
        List<Article> articleList = articleRepository.findAll();

        // 없는 경우
        if(articleList.isEmpty()){
            throw new ArticleException(ArticleErrorCode.NOT_FOUND);
        }

        return articleList;
    }
}
