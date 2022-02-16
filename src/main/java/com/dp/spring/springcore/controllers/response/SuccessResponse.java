package com.dp.spring.springcore.controllers.response;

import com.dp.spring.springcore.controllers.response.body.SuccessBody;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class SuccessResponse extends BaseResponse {
    public SuccessResponse(Map<String, Object> data, HttpStatus httpStatus) {
        super(new SuccessBody(data), httpStatus);
    }

    public SuccessResponse(Map<String, Object> data) {
        this(data, HttpStatus.OK);
    }

    public SuccessResponse(String resultName, Object result, HttpStatus httpStatus) {
        super(new SuccessBody(resultName, result), httpStatus);
    }

    public SuccessResponse(String resultName, Object result) {
       this(resultName, result, HttpStatus.OK);
    }
}
