package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.entity.Reply;

import java.util.ArrayList;
import java.util.List;

public interface ReplyQueryService {
    ArrayList<Reply> getReply(Long articleId);

    List<ReplyResponse> getReplies(Long articlesId, int page);
}
