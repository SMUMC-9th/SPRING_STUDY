package com.example.umc9th.domain.article.exception;

import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralException;

public class ArticleException extends GeneralException {
    public ArticleException(ArticleErrorCode errorCode) {
        super(errorCode);
    }
}
