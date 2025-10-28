package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404-1", "해당 글을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
