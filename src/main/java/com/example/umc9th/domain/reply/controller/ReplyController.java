package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Reply", description = "댓글 관련 API")
@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyConverter replyConverter;
    private final ReplyQueryService replyQueryService;

    @Operation(summary = "댓글 생성", description = "게시글에 댓글을 작성합니다.")
    @PostMapping("/replies")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> createReply(
            @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, replyConverter.toDTO(reply));
    }

    @Operation(summary = "단일 댓글 조회", description = "댓글 ID로 단일 댓글을 조회합니다.")
    @GetMapping("/{replyId}")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> getReply(@PathVariable Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                replyConverter.toDTO(reply)
        );
    }

    @Operation(summary = "offset기반 게시글 댓글 전체 조회", description = "특정 게시글의 댓글 전체를 조회합니다.")
    @GetMapping("/articles/{articleId}/replies/all")
    public Page<ReplyResponseDTO.ReplyResDTO> getReplies(
            @PathVariable Long articleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return replyQueryService.getRepliesByArticle(articleId, page, size);
    }

    @Operation(summary = "게시글 댓글 전체 조회", description = "특정 게시글의 댓글 전체를 조회합니다.")
    @GetMapping("/articles/{articleId}/replies")
    public ApiResponse<List<ReplyResponseDTO.ReplyResDTO>> getRepliesByArticle(@PathVariable Long articleId) {
        List<Reply> replies = replyQueryService.getRepliesByArticle(articleId);

        List<ReplyResponseDTO.ReplyResDTO> responseList = replies.stream()
                .map(replyConverter::toDTO)
                .toList();
        //1. replies.stream() → 리스트를 Stream으로 변환
        //2. .map(replyConverter::toDTO) → Stream 안의 각 Reply를 DTO로 변환
        //3. .toList() → 다시 List로 수집

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseList);
    }


    @Operation(summary = "댓글 수정", description = "댓글 ID로 댓글 내용을 수정합니다.")
    @PatchMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> updateReply(
            @PathVariable Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replyConverter.toDTO(reply));
    }

    @Operation(summary = "댓글 삭제", description = "댓글 ID로 댓글을 삭제합니다.")
    @DeleteMapping("/replies/{replyId}")
    public ApiResponse<Void> deleteReply(@PathVariable Long replyId) {
        replyCommandService.deleteReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
