package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

public interface ReplyQueryService {
    ReplyResponse.ReplyListWithPageDTO getReplyList(Long id, int page, int size);
}
