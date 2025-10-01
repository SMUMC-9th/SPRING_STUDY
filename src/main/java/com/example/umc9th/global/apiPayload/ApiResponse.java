package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private Boolean isSuccess;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    // 200 OK
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<T>(true,
                HttpStatus.OK.toString(),
                HttpStatus.OK.getReasonPhrase(),
                null
        );
    }

    // 201 Created
    public static <T> ApiResponse<T> created() {
        return new ApiResponse<T>(true,
                HttpStatus.CREATED.toString(),
                HttpStatus.CREATED.getReasonPhrase(),
                null
        );
    }

    // 그 이외 성공 응답(result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<T>(true, code.getCode(), code.getMessage(), result);
    }

    // 그 이외 성공 응답(result 미포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code) {
        return new ApiResponse<T>(true, code.getCode(),  code.getMessage(), null );
    }

    // 실패 응답(result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<T>(false, code.getCode(), code.getMessage(), result);
    }

    // 실패 응답(result 미포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code) {
        return new ApiResponse<T>(false, code.getCode(),  code.getMessage(), null );
    }
}
