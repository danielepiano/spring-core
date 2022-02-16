package com.dp.spring.springcore.controllers.response;

import com.dp.spring.springcore.controllers.response.body.BaseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseResponse extends ResponseEntity<BaseBody> {
    public BaseResponse(BaseBody baseBody, HttpStatus httpStatus) {
        super(baseBody, httpStatus);
    }
}
