package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.article.entity.Article;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(readOnly = true)
    public ReplyResponseDTO.ReplyPageResponseDTO getRepliesByArticlePaginated(Long articleId, int page, int size) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, size);
        Page<Reply> replies = replyRepository.findAllByArticleOrderByCreatedAtDesc(article, pageable);

        List<ReplyResponseDTO.ReplyDTO> items = replies.getContent().stream()
            .map(replyConverter::toResponse)
            .collect(Collectors.toList());

        return ReplyResponseDTO.ReplyPageResponseDTO.builder()
            .items(items)
            .pageNo(replies.getNumber())
            .numOfRows(replies.getSize())
            .totalCount((int) replies.getTotalElements())
            .totalPages(replies.getTotalPages())
            .build();
    }
}
