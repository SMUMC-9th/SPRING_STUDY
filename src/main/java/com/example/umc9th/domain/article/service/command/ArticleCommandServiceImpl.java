package com.example.umc9th.domain.article.service.command;


import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.req.ArticleRequestDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    // 게시글 생성
    @Override
    public Article createArticle(
            ArticleRequestDTO.CreateArticleDTO dto
    ) {

        // 객체 생성
        Article article = ArticleConverter.toArticle(dto);

        // save
        return articleRepository.save(article);
    }
}
