package com.dp.spring.springcore.v2.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 404 - NOT FOUND exception to be thrown in business services.
 * <br>
 * It is preferable to extend this class with custom exceptions.
 */
public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(Error error) {
        super(HttpStatus.NOT_FOUND, error);
    }

    public ResourceNotFoundException(Error error, Throwable cause) {
        super(HttpStatus.NOT_FOUND, error, cause);
    }

    public ResourceNotFoundException(Set<Error> errors) {
        super(HttpStatus.NOT_FOUND, errors);
    }

    public ResourceNotFoundException(Set<Error> errors, Throwable cause) {
        super(HttpStatus.NOT_FOUND, errors, cause);
    }
}
