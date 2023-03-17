package com.dp.spring.springcore.exceptions;

import com.dp.spring.springcore.model.error.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General business exception to be thrown in business services.
 * <br>
 * It is preferable that domain custom exceptions extend this class.
 */
public class BusinessException extends RuntimeException {
    protected final HttpStatus status;
    protected final Set<Error> errors;

    public BusinessException(final HttpStatus status, final Error error) {
        super( error.toString() );
        this.status = status;
        this.errors = Set.of(error);
    }

    public BusinessException(final HttpStatus status, final Error error, final Throwable cause) {
        super( error.toString(), cause );
        this.status = status;
        this.errors = Set.of(error);
    }

    public BusinessException(final HttpStatus status, final Set<Error> errors) {
        super( errors.toString() );
        this.status = status;
        this.errors = errors;
    }

    public BusinessException(final HttpStatus status, final Set<Error> errors, final Throwable cause) {
        super( errors.toString(), cause );
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Set<Error> getErrors() {
        return errors;
    }
}