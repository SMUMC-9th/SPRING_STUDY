package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc9th.domain.reply.dto.response.GetReplyResDTO;

public interface ReplyCommandService {
    GetReplyResDTO createReply(ReplyReqDTO dto);
}
