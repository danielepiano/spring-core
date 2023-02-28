package com.dp.spring.springcore.v2.exceptions.examples;

public enum ExceptionConstants {
    DEVICE_NOT_FOUND("0001", "DEVICE NOT FOUND", "Couldn't find device for id %s"),
    NODE_NOT_FOUND("0002", "NODE NOT FOUND", "Couldn't find node");

    private String code;
    private String title;
    private String detail;

    ExceptionConstants(final String code, final String title, final String detail) {
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
