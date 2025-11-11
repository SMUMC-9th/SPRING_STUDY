package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long replyId);

    Page<ReplyResponseDTO.ReplyResDTO> getRepliesByArticle(Long articleId, int page, int size);

    List<Reply> getRepliesByArticle(Long articleId);
}
