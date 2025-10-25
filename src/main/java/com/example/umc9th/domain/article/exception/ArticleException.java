package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class ArticleException extends GeneralException {
    public ArticleException(BaseErrorCode code) {
        super(code);
    }
}
