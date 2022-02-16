package com.dp.spring.springcore.controllers.response.body;

public class ErrorBody extends BaseBody {
    public ErrorBody(String message) {
        super(EStatus.ERROR,null, message);
    }
}
