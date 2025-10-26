package com.example.umc9th.domain.reply.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplySuccessCode implements BaseSuccessCode {

    CREATE(HttpStatus.CREATED,
            "REPLY201",
            "성공적으로 댓글을 생성했습니다."),
    FOUND(HttpStatus.OK,
            "REPLY200",
            "성공적으로 댓글을 조회했습니다."),
    PUT_SUCCESS(HttpStatus.OK,
            "REPLY200",
            "성공적으로 댓글을 수정했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
