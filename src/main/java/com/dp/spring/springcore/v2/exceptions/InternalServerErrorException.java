package com.dp.spring.springcore.v2.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 500 - INTERNAL SERVER ERROR exception to be thrown in business services.
 * <br>
 * It is preferable to extend this class with custom exceptions.
 */
public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException(Error error) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, error);
    }

    public InternalServerErrorException(Error error, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, error, cause);
    }

    public InternalServerErrorException(Set<Error> errors) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errors);
    }

    public InternalServerErrorException(Set<Error> errors, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errors, cause);
    }
}
