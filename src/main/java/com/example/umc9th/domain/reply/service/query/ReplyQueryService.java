package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

import java.util.List;

public interface ReplyQueryService {
    List<ReplyResponse.GetReplyWithArticleIdResDTO> getReplyList(Long id);
}
