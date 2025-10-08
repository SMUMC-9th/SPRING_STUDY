package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{

    private final ReplyRepository replyRepository;
    private final ArticleQueryService articleQueryService;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {

        //게시글을 가져와
        Article article = articleQueryService.getArticle(articleId);

        Reply reply = Reply.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build();

        //게시글에 댓글을 저장
        article.addReply(reply);

        //결과 반환
        return replyRepository.save(reply);
    }
}
