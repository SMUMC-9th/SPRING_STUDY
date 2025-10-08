package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.entity.Reply;

import java.util.ArrayList;

public interface ReplyQueryService {
    ArrayList<Reply> getReply(Long articleId);
}
