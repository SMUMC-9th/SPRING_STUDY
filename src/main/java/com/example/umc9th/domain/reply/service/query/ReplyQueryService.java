package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;

import java.util.List;

public interface ReplyQueryService {
    ReplyResponseDTO.ReplyDTO getReply(Long id);
    List<ReplyResponseDTO.ReplyDTO> getRepliesByArticleId(Long articleId);
}
