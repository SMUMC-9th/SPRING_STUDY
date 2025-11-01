package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.umc9th.domain.reply.converter.ReplyConverter.toGetReplyWithPageResDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyResponse.ReplyListWithPageDTO getReplyList(Long articleId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Reply> replyPage = replyRepository.findByArticleIdOrderByCreatedAtDescIdDesc(articleId, pageable);

        return toGetReplyWithPageResDTO(replyPage);
    }
}
