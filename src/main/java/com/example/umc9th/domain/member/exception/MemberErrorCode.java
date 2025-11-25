package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404_1", "사용자를 찾을 수 없습니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST,"MEMBER400","토큰DTO를 변경 할 수 없습니다.")
    ;

    private final HttpStatus Status;
    private final String code;
    private final String message;


}
