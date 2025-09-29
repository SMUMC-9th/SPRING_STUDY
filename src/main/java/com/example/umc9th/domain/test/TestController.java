package com.example.umc9th.domain.test;

import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import com.example.umc9th.global.apipayload.code.GeneralErrorCode;
import com.example.umc9th.global.apipayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apipayload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API", description = "테스트용 API 입니다.")
public class TestController {

    @GetMapping("/test")
    @Operation(
        summary = "테스트 API",
        description = "테스트용 API 입니다."
    )
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, "테스트 API 호출 성공 결과"));
    }

    @GetMapping("/exception")
    @Operation(
        summary = "예외 테스트 API",
        description = "예외 테스트용 API 입니다."
    )
    public ResponseEntity<ApiResponse<Void>> exception() {
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST_400;
        throw new GeneralException(code);
    }
}
