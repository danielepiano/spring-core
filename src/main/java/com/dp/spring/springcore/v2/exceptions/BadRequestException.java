package com.dp.spring.springcore.v2.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 400 - BAD REQUEST exception to be thrown in business services.
 * It is preferable to extend this class with custom exceptions.
 */
public class BadRequestException extends BusinessException {
    public BadRequestException(Error error) {
        super(HttpStatus.BAD_REQUEST, error);
    }

    public BadRequestException(Error error, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, error, cause);
    }

    public BadRequestException(Set<Error> errors) {
        super(HttpStatus.BAD_REQUEST, errors);
    }

    public BadRequestException(Set<Error> errors, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, errors, cause);
    }
}
