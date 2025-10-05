package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.service.ReplyService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replies")
    public ApiResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyService.createReply(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reply);
    }

    @GetMapping("/replies/{replyId}")
    public ApiResponse<Reply> getReply(@PathVariable Long replyId) {
        Reply reply = replyService.getReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reply);
    }

    @GetMapping("/replies/article/{articleId}")
    public ApiResponse<List<Reply>> getReplies(@PathVariable Long articleId) {
        List<Reply> replies = replyService.getReplies(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }

}
