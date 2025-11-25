package com.example.umc9th.domain.Member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404-1", "회원을 찾을 수 없습니다."),
    BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED,"MEMBER401-1", "비밀번호가 일치하지 않습니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400-1", "OAuth 토큰 발급에 실패했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400-2", "OAuth 사용자 정보 조회에 실패했습니다."),
    OAUTH_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404-2", "OAuth 사용자 이메일 조회에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
