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
            "성공적으로 게시글을 조회했습니다."),
    PATCH_SUCCESS(HttpStatus.OK,
            "ARTICLE200",
            "성공적으로 게시글을 수정했습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "ARTICLE204",
            "반환할 콘텐츠가 없습니다."),
    DELETE(HttpStatus.OK,
            "ARTICLE200",
            "성공적으로 게시글을 삭제했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
