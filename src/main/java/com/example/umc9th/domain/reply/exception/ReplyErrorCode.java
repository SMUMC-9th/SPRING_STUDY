package com.example.umc9th.domain.reply.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {

    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "REPLY404-1", "해당 글의 댓글을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
