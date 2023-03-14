package com.dp.spring.springcore.v2.exceptions;

import com.dp.spring.springcore.v2.model.error.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 401 - UNAUTHORIZED exception to be thrown in business services.
 * It is preferable that domain custom 401 exceptions extend this class.
 */
public class FailedAuthenticationException extends BusinessException {
    public FailedAuthenticationException(Error error) {
        super(HttpStatus.UNAUTHORIZED, error);
    }

    public FailedAuthenticationException(Error error, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, error, cause);
    }

    public FailedAuthenticationException(Set<Error> errors) {
        super(HttpStatus.UNAUTHORIZED, errors);
    }

    public FailedAuthenticationException(Set<Error> errors, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, errors, cause);
    }
}
