package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
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

    // 댓글 생성
    @PostMapping("articles/{articleId}/replies")
    public ApiResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto, @PathVariable long articleId){
        Reply reply = replyCommandService.createReply(dto, articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reply);
    }

    // 게시글에서 댓글 보기
    @GetMapping("articles/{articleId}/replies")
    public ApiResponse<List<Reply>> getReply(@PathVariable long articleId){
        ArrayList<Reply> replies = replyQueryService.getReply(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }

    //게시글에서 댓글들 보기(offset 기반 페이지네이션)
    @GetMapping("articles/{articlesId}/replies")
    public ApiResponse<List<ReplyResponse>> getReplies(@PathVariable Long articlesId, @RequestParam int page){
        List<ReplyResponse> replies = replyQueryService.getReplies(articlesId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }

    // 댓글 수정하기
    @PatchMapping("articles/{articleId}/reply/{replyId}")
    public ApiResponse<Reply> updateReply(@PathVariable("replyId") Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto){
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reply);
    }

    //댓글 삭제하기
    @DeleteMapping("articles/{articleId}/reply/{replyId}")
    public ApiResponse<Long> deleteReply(@PathVariable("replyId") Long replyId){
        replyCommandService.deleteReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replyId);
    }
}
