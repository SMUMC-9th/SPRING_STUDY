package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

public interface ReplyCommandService {
    ReplyResponse.GetReplyResDTO createReply(ReplyReqDTO dto);
}
