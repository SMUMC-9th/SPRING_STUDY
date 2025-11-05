package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.converter.ArticleConverter;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Override
    public ArticleResponseDTO.GetArticleResponseDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return articleConverter.toGetResponse(article);
    }

    @Override
    public List<ArticleResponseDTO.GetArticlesResponseDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articleConverter.toGetListResponse(articles);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleCursorResponseDTO getArticlesByIdCursor(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> articles;

        if (cursor == null || cursor.isEmpty()) {
            articles = articleRepository.findAllByOrderByIdDesc(pageable);
        } else {
            Long cursorId = Long.parseLong(cursor);
            articles = articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
        }

        return buildCursorResponse(articles, "id");
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleCursorResponseDTO getArticlesByCreatedAtCursor(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> articles;

        if (cursor == null || cursor.isEmpty()) {
            articles = articleRepository.findAllByOrderByCreatedAtDescWithId(pageable);
        } else {
            articles = articleRepository.findAllByCreatedAtCursor(cursor, pageable);
        }

        return buildCursorResponse(articles, "created");
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleCursorResponseDTO getArticlesByLikeCursor(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> articles;

        if (cursor == null || cursor.isEmpty()) {
            articles = articleRepository.findAllByLikeNumDesc(pageable);
        } else {
            articles = articleRepository.findAllByLikeCursor(cursor, pageable);
        }

        return buildCursorResponse(articles, "like");
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleResponseDTO.ArticleCursorResponseDTO searchArticlesByTitle(String keyword, String cursor, int size, String sort) {
        if (keyword == null || keyword.isEmpty()) {
            if (sort == null || sort.isEmpty()) {
                sort = "id";
            }

            return switch (sort.toLowerCase()) {
                case "like" -> getArticlesByLikeCursor(cursor, size);
                case "created" -> getArticlesByCreatedAtCursor(cursor, size);
                default -> getArticlesByIdCursor(cursor, size);
            };
        }

        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> articles = articleRepository.searchByTitleWithCursor(keyword, cursor, pageable);

        return buildCursorResponse(articles, "created");
    }

    private ArticleResponseDTO.ArticleCursorResponseDTO buildCursorResponse(Slice<Article> articles, String sortType) {
        List<ArticleResponseDTO.GetArticlesResponseDTO> items = articles.getContent().stream()
            .map(articleConverter::toGetListItemResponse)
            .collect(Collectors.toList());

        String nextCursor = null;
        if (articles.hasNext() && !items.isEmpty()) {
            Article lastArticle = articles.getContent().getLast();
            nextCursor = generateCursor(lastArticle, sortType);
        }

        return ArticleResponseDTO.ArticleCursorResponseDTO.builder()
            .items(items)
            .hasNext(articles.hasNext())
            .cursor(nextCursor)
            .build();
    }

    private String generateCursor(Article article, String sortType) {
        switch (sortType) {
            case "id":
                return String.valueOf(article.getId());
            case "created":
                // 생성 날짜 + ID: "20251015103000_123" (yyyyMMddHHmmss_id)
                String dateFormat = article.getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                return dateFormat + "_" + article.getId();
            case "like":
                // 좋아요 수 패딩: "0000000100_123" (좋아요수_id)
                String paddedLikeNum = String.format("%010d", article.getLikeNum());
                return paddedLikeNum + "_" + article.getId();
            default:
                return String.valueOf(article.getId());
        }
    }
}