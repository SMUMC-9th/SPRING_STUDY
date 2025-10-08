package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc9th.domain.reply.dto.response.GetReplyResDTO;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="REPLY API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("")
    @Operation(method="POST", summary = "댓글 작성 API", description="특정 게시글에 댓글을 작성합니다.")
    public ApiResponse<GetReplyResDTO> createReply(
            @Parameter(description ="작성할 댓글",required = true)
            @RequestBody ReplyReqDTO replyReqDTO
    ) {
        GetReplyResDTO reply = replyCommandService.createReply(replyReqDTO);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED_201, reply);
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "댓글 목록 조회 API", description = "특정 게시글의 댓글 목록을 조회합니다.")
    public ApiResponse<List<GetReplyResDTO>> getReplyList(
            @Parameter(description = "댓글 목록을 조회할 게시글 Id", required = true)
            @PathVariable("articleId") Long articleId
    ) {
        List<GetReplyResDTO> replyList = replyQueryService.getReplyList(articleId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, replyList);
    }
}
