package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})

public class GlobalResponse<T> {

    @JsonProperty("isSuccess")
    private Boolean isSuccess;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    public static <T> GlobalResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return new GlobalResponse<>(
                true,
                        successCode.getCode(),
                        successCode.getMessage(),
                        result
        );
    }

    // 실패 응답
    public static <T> GlobalResponse<T> onFailure(BaseErrorCode errorCode, T result) {
        return new GlobalResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                result
        );
    }

}
