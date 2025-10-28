package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping
    @Operation(
        summary = "댓글 생성 API"
    )
    public ApiResponse<ReplyResponseDTO.ReplyDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        ReplyResponseDTO.ReplyDTO response = replyCommandService.createReply(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    @GetMapping("/{replyId}")
    @Operation(
        summary = "댓글 조회 API"
    )
    public ApiResponse<ReplyResponseDTO.ReplyDTO> getReply(@PathVariable("replyId") Long replyId) {
        ReplyResponseDTO.ReplyDTO response = replyQueryService.getReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping("/articles/{articleId}")
    @Operation(
        summary = "특정 게시글 댓글 전체 조회 API"
    )
    public ApiResponse<List<ReplyResponseDTO.ReplyDTO>> getRepliesByArticle(@PathVariable("articleId") Long articleId) {
        List<ReplyResponseDTO.ReplyDTO> response = replyQueryService.getRepliesByArticleId(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @PutMapping("/{replyId}")
    @Operation(
        summary = "댓글 수정 API"
    )
    public ApiResponse<ReplyResponseDTO.UpdateReplyResponseDTO> updateReply(
        @PathVariable("replyId") Long replyId,
        @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        ReplyResponseDTO.UpdateReplyResponseDTO response = replyCommandService.updateReply(replyId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
