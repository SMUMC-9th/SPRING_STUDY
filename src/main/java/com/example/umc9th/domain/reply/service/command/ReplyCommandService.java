package com.example.umc9th.domain.reply.service.command;

import com.example.umc9th.domain.reply.dto.request.ReplyRequest;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;

public interface ReplyCommandService {
    ReplyResponse.GetReplyResDTO createReply(ReplyRequest.ReplyReqDTO dto);
    ReplyResponse.GetReplyWithArticleIdResDTO patchReply(Long replyId, ReplyRequest.ReplyPatchReqDTO dto);
    void deleteReply(Long replyId);

}
