package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "요청이 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "리소스가 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
