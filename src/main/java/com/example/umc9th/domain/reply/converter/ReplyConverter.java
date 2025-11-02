package com.example.umc9th.domain.reply.converter;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.dto.request.ReplyRequestDTO;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReplyConverter {

    public Reply toEntity(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
            .content(dto.getContent())
            .article(article)
            .build();
    }

    public ReplyResponseDTO.ReplyDTO toResponse(Reply reply) {
        return ReplyResponseDTO.ReplyDTO.builder()
            .id(reply.getId())
            .content(reply.getContent())
            .articleId(reply.getArticle().getId())
            .createdAt(reply.getCreatedAt())
            .updatedAt(reply.getUpdatedAt())
            .build();
    }

    public List<ReplyResponseDTO.ReplyDTO> toListResponse(List<Reply> replies) {
        return replies.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    public ReplyResponseDTO.UpdateReplyResponseDTO toUpdateResponse(Reply reply) {
        return ReplyResponseDTO.UpdateReplyResponseDTO.builder()
            .id(reply.getId())
            .content(reply.getContent())
            .updatedAt(reply.getUpdatedAt())
            .build();
    }

    public ReplyResponseDTO.DeleteReplyResponseDTO toDeleteResponse(Reply reply) {
        return ReplyResponseDTO.DeleteReplyResponseDTO.builder()
            .id(reply.getId())
            .deletedAt(reply.getDeletedAt())
            .build();
    }
}