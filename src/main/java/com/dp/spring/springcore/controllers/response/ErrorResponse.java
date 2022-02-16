package com.dp.spring.springcore.controllers.response;

import com.dp.spring.springcore.controllers.response.body.ErrorBody;
import org.springframework.http.HttpStatus;

public class ErrorResponse extends BaseResponse {
    public ErrorResponse(String message, HttpStatus httpStatus) {
        super( new ErrorBody(message), httpStatus);
    }

    public ErrorResponse(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
