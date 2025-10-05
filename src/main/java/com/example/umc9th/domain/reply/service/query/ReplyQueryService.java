package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {

    Reply getReply(Long id);
    List<Reply> getReplies();
}