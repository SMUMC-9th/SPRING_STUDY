package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.req.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.res.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.exception.code.ReplySuccessCode;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    // 댓글 생성
    @PostMapping("/replies")
    public ApiResponse<ReplyResponseDTO.CreateReply> createReply(
            @RequestBody ReplyRequestDTO.CreateReply dto
    ) {

        // service에서 생성한 댓글 가져오기
        Reply reply = replyCommandService.createReply(dto);

        // 성공 코드 생성
        ReplySuccessCode code = ReplySuccessCode.CREATE;

        // DTO 포장
        ReplyResponseDTO.CreateReply result = ReplyConverter.toCreateReply(reply);

        // 응답 통일
        return ApiResponse.onSuccess(code, result);
    }

    // 특정 댓글 조회
    @GetMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.GetReply> getReply(
            @PathVariable("replyId") Long replyId
    ) {

        // 특정 댓글 조회
        Reply reply = replyQueryService.getReply(replyId);

        // 성공 코드 생성
        ReplySuccessCode code = ReplySuccessCode.FOUND;

        return ApiResponse.onSuccess(code, ReplyConverter.toGetReply(reply));
    }

    // 댓글 수정
    @PutMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.UpdateReply> updateReply(
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReply dto
    ){

        ReplySuccessCode code = ReplySuccessCode.PUT_SUCCESS;
        return ApiResponse.onSuccess(code, replyCommandService.updateReply(replyId, dto));

    }

    // 댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.DeleteReply> deleteReply(
            @PathVariable("replyId") Long replyId
    ){
        ReplySuccessCode code = ReplySuccessCode.DELETE;
        return ApiResponse.onSuccess(code, replyCommandService.deleteReply(replyId));
    }

    // 댓글 Offset 페이징
    @GetMapping("/replies")
    public ApiResponse<ReplyResponseDTO.ReplyOffset> replyOffset(
            @RequestParam(value = "cursor", required = false, defaultValue = "-1") String cursor,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ){
        return ApiResponse.onSuccess(ReplySuccessCode.FOUND, replyQueryService.getReplies(cursor, size));
    }
}