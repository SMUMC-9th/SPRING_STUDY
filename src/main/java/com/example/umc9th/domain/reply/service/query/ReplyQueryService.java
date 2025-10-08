package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.GetReplyWithArticleIdResDTO;

import java.util.List;

public interface ReplyQueryService {
    List<GetReplyWithArticleIdResDTO> getReplyList(Long id);
}
