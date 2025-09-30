package com.example.umc9th.global.apiPayload.exception.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(GeneralException ex) {
        log.warn("[ CustomException ]: {}", ex.getCode().getMessage());
        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode()
                ));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception ex) {
        log.error("[ WARNING ] Internal Server Error : {}", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        ApiResponse<String> errorResponse = ApiResponse.onFailure(
                errorCode
        );
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

}
