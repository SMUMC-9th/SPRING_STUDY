package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("articles/{articleId}")
    public ApiResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto, @PathVariable long articleId){
        Reply reply = replyCommandService.createReply(dto, articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reply);
    }

    //게시글에서 댓글 보기
    @GetMapping("articles/{articleId}/reply")
    public ApiResponse<List<Reply>> getReply(@PathVariable long articleId){
        ArrayList<Reply> replies = replyQueryService.getReply(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }
}
