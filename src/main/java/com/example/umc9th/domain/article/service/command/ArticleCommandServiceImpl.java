package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponse;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponse.GetArticleWithReplyResDTO createArticle(ArticleReqDTO dto) {
        Article article = ArticleConverter.toArticle(dto);

        Article saved = articleRepository.save(article);

        return ArticleConverter.toGetArticleWithReplyResDTO(saved);
    }
}
