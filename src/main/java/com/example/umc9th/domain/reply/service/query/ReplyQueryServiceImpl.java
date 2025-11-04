package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ArticleQueryService articleQueryService;
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ArrayList<Reply> getReply(Long articleId) {
        // 게시글을 가져와서
        Article article = articleQueryService.getArticle(articleId);
        // 게시글에 해당하는 댓글들 가져오기
        return article.getReplies();
    }

    @Override
    public List<ReplyResponse> getReplies(Long articlesId, int page) {

        Article article = articleRepository.findById(articlesId).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        Pageable pageable = PageRequest.of(page, 10);

        //article의 댓글을 생성날짜 내림차순으로 10개씩 조회(offset 기반 page)
        Page<Reply> repliesPage = replyRepository.findAllByArticleOrderByCreatedAtDesc(article, pageable);

        //댓글 내용 가져오기
        List<Reply> replies = repliesPage.getContent();

        //결과를 dto로 변환
        List<ReplyResponse> result = replies.stream().map(ReplyResponse::from).toList();

        return result;
    }
}
