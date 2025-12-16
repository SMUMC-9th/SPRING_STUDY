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
        "사용자를 찾을 수 없습니다."),
    BAD_CREDENTIAL(
        HttpStatus.UNAUTHORIZED,
        "MEMBER_401",
        "아이디 또는 비밀번호가 올바르지 않습니다."),
    OAUTH_TOKEN_FAIL(
        HttpStatus.BAD_REQUEST,
        "MEMBER_4001",
        "OAuth2 토큰을 가져오는데 실패했습니다."),
    OAUTH_USER_INFO_FAIL(
        HttpStatus.BAD_REQUEST,
        "MEMBER_4002",
        "OAuth2 사용자 정보를 가져오는데 실패했습니다."),
    MEMBER_SAVE_FAIL(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "MEMBER_500",
        "사용자 저장에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}