package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.exception.ReplyErrorCode;
import com.example.umc9th.domain.reply.exception.ReplyException;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;
    private final ReplyConverter replyConverter;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDTO.ReplyDTO getReply(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        return replyConverter.toResponse(reply);
    }

    @Override
    public List<ReplyResponseDTO.ReplyDTO> getRepliesByArticleId(Long articleId) {
        articleRepository.findById(articleId)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        List<Reply> replies = replyRepository.findByArticleId(articleId);

        return replyConverter.toListResponse(replies);
    }
}
