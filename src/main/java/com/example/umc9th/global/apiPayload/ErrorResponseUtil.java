package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorResponseUtil {

    public static void sendError(HttpServletResponse response, BaseErrorCode code, ObjectMapper objectMapper) throws IOException {

        response.setStatus(code.getStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        ApiResponse<Object> error = ApiResponse.onFailure(code);

        objectMapper.writeValue(response.getOutputStream(), error);
    }
}
