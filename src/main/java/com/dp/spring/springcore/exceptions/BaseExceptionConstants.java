package com.dp.spring.springcore.exceptions;

import com.dp.spring.springcore.model.error.Error;

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
    ACCESS_DENIED("BAD-001", "ACCESS DENIED", "You are not authorized to access this resource."),
    FIELD_VALIDATION_ERROR("BAD-002", "VALIDATION ERROR", "Field '%s': %s"),
    VALIDATION_ERROR("BAD-003", "VALIDATION ERROR", "Constraint violated: %s");

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
