package com.dp.spring.springcore.controllers.response.body;

import java.util.Map;

public class SuccessBody extends BaseBody {
    public SuccessBody(Map<String, Object> data) {
        super(EStatus.SUCCESS, data, null);
    }
    public SuccessBody(String resultName, Object result) {
        super(EStatus.SUCCESS, resultName, result, null);
    }
}
