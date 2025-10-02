package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK_200(HttpStatus.OK,"COMMON200","요청이 성공적으로 되었습니다."),
    CREATED_201(HttpStatus.CREATED, "COMMON201", "요청이 성공적이었으며 새로운 리소스가 생성되었습니다."),
    ACCEPTED_202(HttpStatus.ACCEPTED, "COMMON202", "요청을 수신하였지만 그에 응하여 행동할 수 없습니다."),
    NO_CONTENT_204(HttpStatus.NO_CONTENT, "COMMON204", "요청에 대해서 보내줄 수 있는 콘텐츠가 없지만, 헤더는 의미있을 수 있습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
