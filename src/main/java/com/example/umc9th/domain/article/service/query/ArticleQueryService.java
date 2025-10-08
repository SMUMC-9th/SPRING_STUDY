package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.GetArticleResDTO;
import com.example.umc9th.domain.article.dto.response.GetArticleWithReplyResDTO;

import java.util.List;

public interface ArticleQueryService {
    GetArticleWithReplyResDTO getArticle(Long id);
    List<GetArticleResDTO> getArticlesList();
}
