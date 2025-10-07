package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService{

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        // 구현, 힌트: findAll()
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        // 구현, 힌트: findById(Long id)
        // findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요
        return articleRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

}
