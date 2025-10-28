package com.example.umc9th.domain.reply.dto.response;

import com.example.umc9th.domain.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReplyResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyDTO {
        private Long id;
        private String content;
        private Long articleId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ReplyDTO from(Reply reply) {
            return ReplyDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .articleId(reply.getArticle().getId())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
        }

        public static List<ReplyDTO> fromList(List<Reply> replies) {
            return replies.stream()
                .map(ReplyDTO::from)
                .collect(Collectors.toList());
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReplyResponseDTO {
        private Long id;
        private String content;
        private LocalDateTime updatedAt;

        public static UpdateReplyResponseDTO from(Reply reply) {
            return UpdateReplyResponseDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .updatedAt(reply.getUpdatedAt())
                .build();
        }
    }
}
