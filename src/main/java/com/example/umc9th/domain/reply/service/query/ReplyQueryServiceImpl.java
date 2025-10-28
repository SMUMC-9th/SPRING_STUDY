package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.umc9th.domain.reply.converter.ReplyConverter.toGetReplyWithArticleIdResDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyResponse.GetReplyWithArticleIdResDTO> getReplyList(Long articleId) {
        List<Reply> replyList = replyRepository.findByArticleId(articleId);

        return toGetReplyWithArticleIdResDTO(replyList);
    }
}
