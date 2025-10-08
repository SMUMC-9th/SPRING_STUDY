package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import com.example.umc9th.global.apipayload.exception.GeneralException;

public class ArticleException extends GeneralException {

    public ArticleException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
