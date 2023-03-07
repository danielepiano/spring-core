package com.dp.spring.springcore.v2.exceptions;

import com.dp.spring.springcore.v2.model.error.Error;

/**
 * Constants for provided base exceptions.
 */
public enum BaseExceptionConstants {
    // ########## INTERNAL SERVER ERRORS ##########
    INTERNAL_SERVER_ERROR("ISE-000", "INTERNAL SERVER ERROR", "No details available."),
    UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR("ISE-001", "UNSUPPORTED OPERATION",
            "This class should not be instantiated."),
    SERVICE_UNAVAILABLE("ISE-002", "SERVICE UNAVAILABLE", "Could not contact some services."),


    // ########## CLIENT ERRORS ##########
    ACCESS_DENIED("CLI-001", "ACCESS DENIED", "Unauthorized."),
    VALIDATION_EXCEPTION("CLI-002", "VALIDATION EXCEPTION", "Field '%s': %s");



    private final String code;
    private final String title;
    private final String detail;

    BaseExceptionConstants(final String code, final String title, final String detail) {
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


    public Error buildError() {
        return new Error(this.code, this.title, this.detail);
    }
}
