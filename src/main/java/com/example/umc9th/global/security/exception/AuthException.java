package com.example.umc9th.global.security.exception;

import com.example.umc9th.global.exception.GeneralException;

public class AuthException extends GeneralException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
