package com.example.umc9th.domain.test;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API")
public class TestController {

    @GetMapping("/test")
    @Operation(
            summary = "테스트하는 API",
            description = "요청을 보내면 Hello World를 응답으로 보냅니다."
    )
    public ApiResponse<String> test(){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK_200, "Hello World");
    }

    @GetMapping("/exception")
    @Operation(
            summary = "예외 테스트하는 API",
            description = "예외를 터뜨립니다."
    )
    public ApiResponse<Void> exception(){
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST_400;
        throw new GeneralException(code);
    }
}
