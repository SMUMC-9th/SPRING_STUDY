package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_404", "게시글을 찾을 수 없습니다.")
    ;

    private final HttpStatus Status;
    private final String code;
    private final String message;


}
