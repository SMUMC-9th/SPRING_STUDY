package com.example.umc9th.domain.reply.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REPLY404",
            "해당 댓글이 존재하지 않습니다."),
    NOT_LINK(HttpStatus.FORBIDDEN,
            "REPLY403",
            "해당 댓글과 게시글이 연동되어있지 않습니다."),
    PUT_BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "REPLY401",
            "해당 댓글을 수정할 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
