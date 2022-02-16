package com.dp.spring.springcore.controllers.response.body;

import java.util.Map;

public class FailBody extends BaseBody {
    public FailBody(Map<String, Object> data) {
        super(EStatus.FAIL, data, null);
    }

    public FailBody(String failurePoint, String details) {
       super(EStatus.FAIL, failurePoint, details, null);
    }
}
