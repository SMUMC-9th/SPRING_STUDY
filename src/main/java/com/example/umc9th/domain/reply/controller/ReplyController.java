package com.example.umc9th.domain.reply.controller;

import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.service.command.ReplyCommandService;
import com.example.umc9th.domain.reply.service.query.ReplyQueryService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyConverter replyConverter;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/replies")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> createReply(
            @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, replyConverter.toDTO(reply));
    }

    @GetMapping("/{replyId}")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> getReply(@PathVariable Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                replyConverter.toDTO(reply)
        );
    }
    @GetMapping("/article/{articleId}")
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


    @PatchMapping("/replies/{replyId}")
    public ApiResponse<ReplyResponseDTO.ReplyResDTO> updateReply(
            @PathVariable Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, replyConverter.toDTO(reply));
    }

    @DeleteMapping("/replies/{replyId}")
    public ApiResponse<Void> deleteReply(@PathVariable Long replyId) {
        replyCommandService.deleteReply(replyId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
