package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;

public interface ReplyCommandService {
    ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto);
}
