package com.example.umc9th.domain.reply.service.command;


import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;

    // 게시글 생성
    @Override
    public Reply createReply(
            ReplyRequestDTO.CreateReply dto
    ) {

        // 객체 생성
        Reply reply = ReplyConverter.toReply(dto);

        // 게시글 연동: 게시글 조회
//        Article article = articleRepository.findById(id)
//                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        // save
        return replyRepository.save(reply);
    }
}
