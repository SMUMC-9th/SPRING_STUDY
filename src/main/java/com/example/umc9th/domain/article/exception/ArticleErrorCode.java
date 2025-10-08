package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404", "게시글을 찾을 수 없습니다."),
    ARTICLE_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "ARTICLE400", "잘못된 게시글 요청입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
