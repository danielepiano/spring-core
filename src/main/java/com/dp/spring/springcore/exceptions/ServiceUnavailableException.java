package com.dp.spring.springcore.exceptions;

import org.springframework.http.HttpStatus;

/**
 * General 503 - SERVICE UNAVAILABLE exception to be thrown in business services.
 */
public class ServiceUnavailableException extends BusinessException {
    public ServiceUnavailableException() {
        super(HttpStatus.SERVICE_UNAVAILABLE, BaseExceptionConstants.SERVICE_UNAVAILABLE.buildError());
    }

    public ServiceUnavailableException(Throwable cause) {
        super(HttpStatus.SERVICE_UNAVAILABLE, BaseExceptionConstants.SERVICE_UNAVAILABLE.buildError(), cause);
    }
}
