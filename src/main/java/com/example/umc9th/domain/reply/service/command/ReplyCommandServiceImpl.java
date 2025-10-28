package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;
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
    public ReplyResponse.GetReplyResDTO createReply(ReplyReqDTO dto) {
        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        Reply reply = ReplyConverter.toReply(dto, article);

        Reply saved = replyRepository.save(reply);

        return ReplyConverter.toGetReplyResDTO(saved);
    }
}
