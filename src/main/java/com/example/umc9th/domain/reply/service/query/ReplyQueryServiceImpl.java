package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.domain.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ArticleQueryService articleQueryService;

    @Override
    public ArrayList<Reply> getReply(Long articleId) {
        // 게시글을 가져와서
        Article article = articleQueryService.getArticle(articleId);
        // 게시글에 해당하는 댓글들 가져오기
        return article.getReplies();
    }
}
