package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.request.ReplyRequest;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.exception.ReplyErrorCode;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponse.GetReplyResDTO createReply(ReplyRequest.ReplyReqDTO dto) {
        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        Reply reply = ReplyConverter.toReply(dto, article);

        Reply saved = replyRepository.save(reply);

        return ReplyConverter.toGetReplyResDTO(saved);
    }

    @Override
    public ReplyResponse.GetReplyWithArticleIdResDTO patchReply(Long replyId, ReplyRequest.ReplyPatchReqDTO dto) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(ReplyErrorCode.REPLY_NOT_FOUND));

        reply.updateReply(dto.content());

        return ReplyConverter.toGetReplyWithArticleIdResDTO(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(ReplyErrorCode.REPLY_NOT_FOUND));

        replyRepository.delete(reply);
    }
}
