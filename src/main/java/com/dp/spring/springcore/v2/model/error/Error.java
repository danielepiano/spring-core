package com.dp.spring.springcore.v2.model.error;

import java.util.Objects;

/**
 * General single error accompanying a business exception thrown in business services.
 */
public record Error(String code, String title, String detail) {
    public Error {
        Objects.requireNonNull(title);
    }

    @Override
    public String toString() {
        return "[" + this.code + "] " + this.title + ": " + this.detail;
    }
}
