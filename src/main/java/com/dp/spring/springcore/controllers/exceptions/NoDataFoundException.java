package com.dp.spring.springcore.controllers.exceptions;

import lombok.Getter;
import lombok.Setter;

public class NoDataFoundException extends RuntimeException {
    @Getter @Setter
    private String failurePoint;

    public NoDataFoundException(String failurePoint, String details) {
        super(details);
        this.failurePoint = failurePoint;
    }

    public NoDataFoundException() {
        this("", "No data found.");
    }

    public String getDetails() {
        return super.getMessage();
    }
}
