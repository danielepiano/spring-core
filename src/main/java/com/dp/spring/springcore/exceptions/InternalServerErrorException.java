package com.dp.spring.springcore.exceptions;

import com.dp.spring.springcore.model.error.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * General 500 - INTERNAL SERVER ERROR exception to be thrown in business services.
 * <br>
 * It is preferable that domain custom 5xx exceptions extend this class.
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
