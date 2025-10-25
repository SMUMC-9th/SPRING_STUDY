package com.example.umc9th.domain.article.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleSuccessCode implements BaseSuccessCode {

    CREATE(HttpStatus.CREATED,
            "ARTICLE201",
            "성공적으로 게시글을 생성했습니다."),
    FOUND(HttpStatus.OK,
            "ARTICLE200",
            "성공적으로 게시글을 조회했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
