package com.example.umc9th.domain.reply.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class ReplyException extends GeneralException {
    public ReplyException(BaseErrorCode code) {
        super(code);
    }
}
