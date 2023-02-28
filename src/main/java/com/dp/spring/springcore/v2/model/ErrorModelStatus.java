package com.dp.spring.springcore.v2.model;

import org.springframework.http.HttpStatus;

public enum ErrorModelStatus {
    FAIL("fail"),
    ERROR("error"),
    GENERIC("generic");

    private final String value;

    ErrorModelStatus(final String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static String valueOf(final HttpStatus status) {
        if (status.is4xxClientError()) {
            return FAIL.value();
        }
        if (status.is5xxServerError()) {
            return ERROR.value();
        }
        return GENERIC.value();
    }
}
