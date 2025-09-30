package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

//생성자로 객체를 생성하는 것을 막기
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄 것인지 결정하는 Annotation
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess") // 변수 이름이 isSuccess라는 것을 명시하기 위한 Annotation
    private Boolean isSuccess;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;



    //성공했을 때 응답
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        //결과 값을 담는 변수를 추가하고, 이곳에 추가하기(제네릭 사용하기)
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        //결과 값을 담는 변수를 추가하고, 이곳에 추가하기(제네릭 사용하기)
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
