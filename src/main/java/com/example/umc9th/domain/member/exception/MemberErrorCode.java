package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404", "사용자를 찾을 수 없습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER_400", "잘못된 인증 정보입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "MEMBER_401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "MEMBER_403", "접근 권한이 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER_409", "이미 존재하는 이메일입니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "MEMBER_409", "이미 존재하는 사용자명입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER_401", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER_401", "만료된 토큰입니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST,"MEMBER400","토큰DTO를 변경 할 수 없습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.FORBIDDEN,"MEMBER_403_2","사용자 정보를 가져올 수 없습니다.")
    ;

    private final HttpStatus Status;
    private final String code;
    private final String message;
}
