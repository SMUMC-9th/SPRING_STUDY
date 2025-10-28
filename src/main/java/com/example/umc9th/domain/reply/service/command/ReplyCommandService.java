package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId);
    Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto);
    void deleteReply(Long replyId);
}
