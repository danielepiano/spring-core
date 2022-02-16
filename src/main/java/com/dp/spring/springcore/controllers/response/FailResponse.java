package com.dp.spring.springcore.controllers.response;

import com.dp.spring.springcore.controllers.response.body.FailBody;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class FailResponse extends BaseResponse {
    public FailResponse(Map<String, Object> data, HttpStatus httpStatus) {
        super(new FailBody(data), httpStatus);
    }

    public FailResponse(Map<String, Object> data) {
        this(data, HttpStatus.BAD_REQUEST);
    }

    public FailResponse(String failurePoint, String details, HttpStatus httpStatus) {
        super(new FailBody(failurePoint, details), httpStatus);
    }

    public FailResponse(String failurePoint, String details) {
        this(failurePoint, details, HttpStatus.BAD_REQUEST);
    }
}
