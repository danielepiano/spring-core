package com.dp.spring.springcore.controllers.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ItemNotFoundException extends RuntimeException {
    @Getter @Setter
    private String failurePoint;

    public ItemNotFoundException(String failurePoint, String details) {
        super(details);
        this.failurePoint = failurePoint;
    }

    public ItemNotFoundException() {
        this("", "Item not found.");
    }

    public String getDetails() {
        return super.getMessage();
    }
}
