package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK_200(HttpStatus.OK,
       "COMMON200",
            "성공적으로 요청을 처리했습니다."),
    CREATED_201(HttpStatus.CREATED,
            "COMMON201",
            "성공적으로 객체를 생성했습니다."),

    ;

    private final HttpStatus status;
    private final String message;
    private final String code;
}
