package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc9th.domain.reply.service.ReplyService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor

public class ReplyController {

    private final ReplyService replyService;

    //댓글 생성
    @PostMapping("/articles/{articleId}/replies")
    public ApiResponse<ReplyResponseDTO.ReplyDTO> createReply(
            @PathVariable Long articleId,
            @RequestBody ReplyRequestDTO.CreateReplyDTO request
    ) {
        ReplyResponseDTO.ReplyDTO response = replyService.createReply(articleId,request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    //댓글 단건 조회
    @GetMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.ReplyDTO> getReply(
            @PathVariable Long replyId
    ) {
        ReplyResponseDTO.ReplyDTO response = replyService.getReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //댓글 목록 조회
    @GetMapping("/articles/{articleId}/replies")
    public ApiResponse<ReplyResponseDTO.ReplyListDTO> getRepliesByArticle(
            @PathVariable Long articleId
    ) {
        ReplyResponseDTO.ReplyListDTO response = replyService.getRepliesByArticle(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

}
