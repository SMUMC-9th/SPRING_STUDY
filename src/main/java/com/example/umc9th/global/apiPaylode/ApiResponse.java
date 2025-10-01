package com.example.umc9th.global.apiPaylode;

import com.example.umc9th.global.code.BaseErrorCode;
import com.example.umc9th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

// 생성자로 객체를 생성하는 것을 막기
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 Annotation
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess") // 변수 이름이 isSuccess라는 것을 명시하기 위한 Annotation
    private Boolean isSuccess;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private final T result;

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<T>(true, code.getCode(), code.getMessage(), result);
    }


    //데이터 포함
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<T>(false, code.getCode(), code.getMessage(), result);
    }
    //데이터가 없는 실패 응답 매서드
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code) {
        return new ApiResponse<T>(false, code.getCode(), code.getMessage(), null);
    }


//    // 기본적으로 200 OK를 반환하는 매서드 성공했을때 응답
//    public static <T> ApiResponse<T> onSuccess(T result) {
//        return new ApiResponse(true, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), result);
//    }
//    //상태 코드를 받아서 사용하는 성공 응답 생성 메서드
//    public static <T> ApiResponse<T> onSuccess(HttpStatus status, T result) {
//        return new ApiResponse<>(true, String.valueOf(status.value()), status.getReasonPhrase(), result);
//    }
//
//    //실패 응답 생성 메서드 (데이터 포함)
//    public static <T> ApiResponse<T> onFailure(String code, String message, T result) {
//        return new ApiResponse<>(false, code, message, result);
//    }
//
//    //실패 응답 생성 메서드 (데이터 없음)
//    public static <T> ApiResponse<T> onFailure(String code, String message) {
//        return new ApiResponse<>(false, code, message, null);
//    }
}

