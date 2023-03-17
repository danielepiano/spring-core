package com.dp.spring.springcore.model.error;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum ErrorModelStatus {
    FAIL("fail"),
    ERROR("error"),
    GENERIC("generic");

    private final String value;

    ErrorModelStatus(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    public static ErrorModelStatus valueOf(final HttpStatus status) {
        if (status.is4xxClientError()) {
            return FAIL;
        }
        if (status.is5xxServerError()) {
            return ERROR;
        }
        return GENERIC;
    }
}
