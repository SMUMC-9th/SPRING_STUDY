package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.res.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;

public interface ReplyQueryService {

    Reply getReply(Long id);

    ReplyResponseDTO.ReplyOffset getReplies(
            String cursor,
            Integer size
    );
}