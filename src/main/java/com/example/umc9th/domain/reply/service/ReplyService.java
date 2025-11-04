package com.example.umc9th.domain.reply.service;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    //댓글 생성
    @Transactional
    public ReplyResponseDTO.ReplyDTO createReply(
            Long articleId,
            ReplyRequestDTO.CreateReplyDTO request
    ) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        Reply reply = ReplyConverter.toReply(request, article);
        Reply saved = replyRepository.save(reply);
        return ReplyConverter.toReplyDTO(saved);
    }

    //댓글 조회
    @Transactional(readOnly = true)
    public ReplyResponseDTO.ReplyDTO getReply(
            Long replyId
    ) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        return ReplyConverter.toReplyDTO(reply);
    }

    //댓글 목록 조회
    @Transactional(readOnly = true)
    public ReplyResponseDTO.ReplyListDTO getRepliesByArticle(
            Long articleId
    ) {
        List<Reply> list = replyRepository.findByArticleId(articleId);
        return ReplyConverter.toListDTO(list);
    }

    //전체 수정
    @Transactional
    public ReplyResponseDTO.ReplyDTO putReply(
            Long id,
            ReplyRequestDTO.PutDTO dto
    ) {
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        reply.update(dto.content());
        return ReplyConverter.toReplyDTO(reply);

    }

    //부분 수정
    @Transactional
    public ReplyResponseDTO.ReplyDTO patchReply(
            Long id,
            ReplyRequestDTO.PatchDTO dto
    ) {
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        if(dto.content() != null){
            reply.patch(dto.content());
        }
        return ReplyConverter.toReplyDTO(reply);

    }

    //삭제
    @Transactional
    public Long deleteReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new GeneralException(GeneralErrorCode.NOT_FOUND_404)
        );
        replyRepository.delete(reply);
        return id;
    }

    //댓글 조회 페이지네이션
    @Transactional(readOnly = true)
    public ReplyResponseDTO.ReplyPageDTO getRepliesByArticleWithPagination(
            Long articleId,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ReplyConverter.toPageDTO(
                replyRepository.findAllByArticleIdOrderByCreatedAtDesc(articleId, pageable));
    }

}
