package com.dp.spring.springcore.controllers.response.body;

public enum EStatus {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private final String status;

    EStatus(String status) {
        this.status = status;
    }

    String status() {
        return status;
    }
}
