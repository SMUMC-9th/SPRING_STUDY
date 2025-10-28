package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.article.service.query.ArticleQueryService;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
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

    @Override
    public Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        reply.update(dto.getTitle(), dto.getContent());
        return reply;
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        replyRepository.delete(reply);
    }
}
