package com.example.umc9th.domain.reply.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import com.example.umc9th.global.apipayload.exception.GeneralException;

public class ReplyException extends GeneralException {
    
    public ReplyException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
