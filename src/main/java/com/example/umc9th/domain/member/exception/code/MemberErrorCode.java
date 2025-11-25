package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    //401
    BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "유효한 인증 자격이 아닙니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER401_2", "소셜 로그인 실패"),
    OAUTH_USER_INFO_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER401_3", "소셜 로그인에서 사용자 정보를 가져오지 못했습니다."),

    //404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 ID의 맴버를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
