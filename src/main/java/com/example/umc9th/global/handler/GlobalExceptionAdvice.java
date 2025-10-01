package com.example.umc9th.global.handler;

import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.code.BaseErrorCode;
import com.example.umc9th.global.exception.GeneralErrorCode;
import com.example.umc9th.global.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse> handleCustomException(
            GeneralException ex
    ) {
        //예외가 발생하면 로그 기록
        log.warn("[ CustomException ]: {}", ex.getCode().getMessage());
        //커스텀 예외에 정의된 에러 코드와 메시지를 포함한 응답 제공
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode(),// 구현한 응답 통일에 맞춰 조정 필요!
                        ex.getMessage()
                ));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse> handleAllException(
            Exception ex
    ) {
        log.error("[WARNING] Internal Server Error : {} ", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        ApiResponse errorResponse = ApiResponse.onFailure(
                errorCode, // 구현한 응답 통일에 맞춰 조정 필요!
                null       //result 값, 오버로드 하면 없게 해도됨
        );
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }
}
