package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "MEMBER_404",
        "사용자를 찾을 수 없습니다."
    ),
    BAD_CREDENTIAL(
        HttpStatus.UNAUTHORIZED,
        "MEMBER_401",
        "아이디 또는 비밀번호가 올바르지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}