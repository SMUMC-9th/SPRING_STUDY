package com.example.umc9th.domain.reply.service;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        Reply reply = Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();

        return replyRepository.save(reply);
    }

    @Transactional(readOnly = true)
    public Reply getReply(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

    @Transactional
    public List<Reply> getReplies(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }
}
