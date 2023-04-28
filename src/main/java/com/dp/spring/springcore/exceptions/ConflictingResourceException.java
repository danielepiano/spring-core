package com.dp.spring.springcore.exceptions;

import com.dp.spring.springcore.model.error.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 409 - CONFLICT exception to be thrown in business services.
 * <br>
 * It is preferable that domain custom 409 exceptions extend this class.
 */
public class ConflictingResourceException extends BusinessException {
    public ConflictingResourceException(Error error) {
        super(HttpStatus.CONFLICT, error);
    }

    public ConflictingResourceException(Error error, Throwable cause) {
        super(HttpStatus.CONFLICT, error, cause);
    }

    public ConflictingResourceException(Set<Error> errors) {
        super(HttpStatus.CONFLICT, errors);
    }

    public ConflictingResourceException(Set<Error> errors, Throwable cause) {
        super(HttpStatus.CONFLICT, errors, cause);
    }
}
