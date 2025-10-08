package com.example.umc9th.domain.article.service.command;

import com.example.umc9th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc9th.domain.article.dto.response.GetArticleWithReplyResDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.umc9th.domain.article.converter.ArticleConverter.toGetArticleResDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public GetArticleWithReplyResDTO createArticle(ArticleReqDTO dto) {
        Article article = articleRepository.save(
                Article.builder()
                        .title(dto.title())
                        .content(dto.content())
                        .build()
        );
        return toGetArticleResDTO(article);
    }
}
