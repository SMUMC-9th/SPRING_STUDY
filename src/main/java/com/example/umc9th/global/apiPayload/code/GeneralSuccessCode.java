package com.example.umc9th.global.apiPayload.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "COMMON200", "요청 성공"),
    CREATED(HttpStatus.CREATED, "COMMON201", "리소스 생성 성공"), ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
