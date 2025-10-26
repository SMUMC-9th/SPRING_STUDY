package com.example.umc9th.domain.reply.service.command;


import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.res.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.exception.ReplyException;
import com.example.umc9th.domain.reply.exception.code.ReplyErrorCode;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    // 댓글 생성
    @Override
    public Reply createReply(
            ReplyRequestDTO.CreateReply dto
    ) {

        // 게시글 연동: 게시글 조회
        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        // 객체 생성
        Reply reply = ReplyConverter.toReply(dto, article);

        // save
        return replyRepository.save(reply);
    }

    // 댓글 수정 (PUT)
    @Override
    public ReplyResponseDTO.UpdateReply updateReply(
            Long replyId,
            ReplyRequestDTO.UpdateReply dto
    ){

        // DTO 모두 존재하는지 확인
        if (dto.content() == null || dto.articleId() == null){
            throw new ReplyException(ReplyErrorCode.PUT_BAD_REQUEST);
        }

        // 존재 여부 확인
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.NOT_FOUND));

        // 게시글 존재 여부 확인
        Article article = articleRepository.findById(dto.articleId())
                        .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        reply.updateContent(dto.content());
        reply.updateArticle(article);

        return ReplyConverter.toUpdateReply(reply);
    }

    // 댓글 삭제
    @Override
    public ReplyResponseDTO.DeleteReply deleteReply(
            Long replyId
    ){
        // 존재여부 확인
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.NOT_FOUND));

        // 삭제 처리
        replyRepository.delete(reply);

        return ReplyConverter.toDeleteReply(reply);
    }
}
