package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.GetReplyResDTO;

import java.util.List;

public interface ReplyQueryService {
    List<GetReplyResDTO> getReplyList(Long id);
}
