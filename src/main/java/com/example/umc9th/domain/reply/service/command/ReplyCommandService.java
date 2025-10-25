package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;

public interface ReplyCommandService {

    Reply createReply(ReplyRequestDTO.CreateReply dto);
}