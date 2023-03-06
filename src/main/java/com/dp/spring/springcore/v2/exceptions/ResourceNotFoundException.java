package com.dp.spring.springcore.v2.exceptions;

import com.dp.spring.springcore.v2.model.error.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 404 - NOT FOUND exception to be thrown in business services.
 * <br>
 * It is preferable that domain custom 404 exceptions extend this class.
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
