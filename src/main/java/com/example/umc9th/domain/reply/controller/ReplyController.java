package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.request.ReplyRequest;
import com.example.umc9th.domain.reply.dto.response.ReplyResponse;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="REPLY API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("")
    @Operation(method="POST", summary = "댓글 작성 API", description="특정 게시글에 댓글을 작성합니다.")
    public ApiResponse<ReplyResponse.GetReplyResDTO> createReply(
            @Parameter(description ="작성할 댓글",required = true)
            @RequestBody ReplyRequest.ReplyReqDTO replyReqDTO
    ) {
        ReplyResponse.GetReplyResDTO reply = replyCommandService.createReply(replyReqDTO);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED_201, reply);
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "댓글 목록 조회 API", description = "특정 게시글의 댓글 목록을 조회합니다.")
    public ApiResponse<ReplyResponse.ReplyListWithPageDTO> getReplyList(
            @Parameter(description = "댓글 목록을 조회할 게시글 Id", required = true)
            @PathVariable("articleId") Long articleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReplyResponse.ReplyListWithPageDTO replyList = replyQueryService.getReplyList(articleId, page, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, replyList);
    }

    @PutMapping("/patch/{replyId}")
    @Operation(method = "PATCH", summary = "특정 Reply 수정 API", description = "특정 댓글을 수정합니다.")
    public ApiResponse<ReplyResponse.GetReplyWithArticleIdResDTO> patchReply(
            @Parameter(description = "수정할 reply id")
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyRequest.ReplyPatchReqDTO replyReqDTO
    ) {
        ReplyResponse.GetReplyWithArticleIdResDTO reply = replyCommandService.patchReply(replyId, replyReqDTO);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED_201, reply);
    }

    @DeleteMapping("/delete/{replyId}")
    @Operation(method = "DELETE", summary = "특정 Reply 삭제 API", description = "특정 댓글을 삭제합니다.")
    public ApiResponse<Void> deleteReply(
            @Parameter(description = "삭제할 Reply Id")
            @PathVariable("replyId") Long replyId
    ) {
        replyCommandService.deleteReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.NO_CONTENT_204, null);
    }
}
