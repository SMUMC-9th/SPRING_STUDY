package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}
