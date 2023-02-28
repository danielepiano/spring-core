package com.dp.spring.springcore.v2.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General business exception to be thrown in business services.
 * <br>
 * It is preferable to extend this class or a subclass of it, with custom exceptions.
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