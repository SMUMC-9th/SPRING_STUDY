package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc9th.domain.reply.service.ReplyService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
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

    //전체 수정
    @PutMapping("/replies/{id}")
    public ApiResponse<ReplyResponseDTO.ReplyDTO> put(
            @PathVariable Long id,
            @Valid @RequestBody ReplyRequestDTO.PutDTO dto
    ) {
        ReplyResponseDTO.ReplyDTO response = replyService.putReply(id, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //부분 수정
    @PatchMapping("/replies/{id}")
    public ApiResponse<ReplyResponseDTO.ReplyDTO> patch(
            @PathVariable Long id,
            @Valid @RequestBody ReplyRequestDTO.PatchDTO dto
    ) {
        ReplyResponseDTO.ReplyDTO response = replyService.patchReply(id, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    //삭제
    @DeleteMapping("/replies/{id}")
    public ApiResponse<Long> delete(@PathVariable Long id) {
        Long deletedId = replyService.deleteReply(id);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, deletedId);
    }

    // 댓글 목록 페이지네이션
    @GetMapping("/articles/{articleId}/replies/page")
    public ApiResponse<ReplyResponseDTO.ReplyPageDTO> getRepliesByArticleWithPagination(
            @PathVariable Long articleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReplyResponseDTO.ReplyPageDTO response =
                replyService.getRepliesByArticleWithPagination(articleId, page, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }


}
