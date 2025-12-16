package com.example.umc9th.global.auth.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    OAUTH_TOKEN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH500",
            "토큰을 발급받을 수 없습니다."),
    OAUTH_EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST,
            "OAUTH400",
            "이메일 정보를 제공받지 못했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH500",
            "사용자 정보를 가져오는데 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
