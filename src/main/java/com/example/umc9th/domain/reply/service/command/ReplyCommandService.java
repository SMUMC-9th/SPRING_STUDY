package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.res.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;

public interface ReplyCommandService {

    Reply createReply(ReplyRequestDTO.CreateReply dto);

    // 댓글 수정 (PUT)
    ReplyResponseDTO.UpdateReply updateReply(
            Long replyId,
            ReplyRequestDTO.UpdateReply dto
    );

    // 댓글 삭제
    ReplyResponseDTO.DeleteReply deleteReply(
            Long replyId
    );
}