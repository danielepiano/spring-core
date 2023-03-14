package com.dp.spring.springcore.v2.handlers;

import com.dp.spring.springcore.v2.exceptions.BusinessException;
import com.dp.spring.springcore.v2.exceptions.ServiceUnavailableException;
import com.dp.spring.springcore.v2.handlers.strategies.*;
import com.dp.spring.springcore.v2.model.error.ErrorModel;
import feign.RetryableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Basic exceptions handler definition.
 * <br>
 * If an exception occurs due to a server error, the related information is omitted for security reasons.
 * <br><br>
 * ! In order to use this configuration, you have to
 * <ul>
 *     <li>Set:        <i>spring.mvc.throw-exception-if-no-handler-found=true in application.properties</i></li>
 *     <li>Create:     <i>@{@link ControllerAdvice} ConcreteExceptionHandler extends {@link BaseExceptionHandler}</i></li>
 * </ul>
 */
@ControllerAdvice
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Strategy to use to handle the exception caught.
     */
    private HandlingExceptionStrategy strategy;

    /**
     * Process the appropriate HTTP response according to the set strategy.
     * @param e the exception caught
     * @return the appropriate response according to the
     */
    private ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        if ( this.strategy != null  &&  e != null ) {
            return this.strategy.handle(e, status);
        }
        return null;
    }

    /**
     * Generic Exceptions will result in an undefined Internal Server Error.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleGenericException(Exception e) {
        this.strategy = CensorAsInternalServerErrorHandlingStrategy.getInstance();
        return this.handle(e, null);
    }

    /**
     * {@link BusinessException}s information will be censored if due to server errors.
     * <br>
     * Any error information can still be read through console logs.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {
        if ( e.getStatus().is5xxServerError() ) {
            this.strategy = CensorErrorsDetailHandlingStrategy.getInstance();
        }
        else {
            this.strategy = PreserveErrorsInformationHandlingStrategy.getInstance();
        }
        return this.handle(e, e.getStatus());
    }

    /**
     * {@link AccessDeniedException}s are typically triggered when an authenticated user attempts to access a resource
     * or perform an operation for which they do not have sufficient privileges or permissions.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleAccessDeniedException(AccessDeniedException e) {
        this.strategy = AccessDeniedHandlingStrategy.getInstance();
        return this.handle(e, null);
    }

    /**
     * {@link MethodArgumentNotValidException}s are typically triggered when there are validation errors in the
     * arguments of a method in a Spring MVC controller (e.g. required or not valid fields).
     * <br>
     * Override in order to return a body consistent with {@link ErrorModel}.
     * @param e the exception to handle
     * @param headers the response headers up to that moment
     * @param statusCode the status code designated by {@link ResponseEntityExceptionHandler}
     * @param request the request
     * @return the appropriate response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest request) {
        this.strategy = ValidationErrorsHandlingStrategy.getInstance();
        var response = this.handle(e, HttpStatus.valueOf(statusCode.value()));
        return new ResponseEntity<>( response != null ? response.getBody() : null, headers, statusCode);
    }

    /**
     * {@link RetryableException}s are typically triggered by temporary problems that could be resolved later,
     * such as microservices connection problems or network problems.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    protected ResponseEntity<ErrorModel> handleRetryableException(RetryableException e) {
        this.strategy = PreserveErrorsInformationHandlingStrategy.getInstance();
        return this.handle(new ServiceUnavailableException(),  HttpStatus.SERVICE_UNAVAILABLE);
    }



    /**
     * Exception whose handling is managed by Spring with {@link ResponseEntityExceptionHandler} and not override,
     * finally invoke this method.
     * <br>
     * Override in order to return a body consistent with {@link ErrorModel}.
     * @param ex the exception to handle
     * @param body the response body up to that moment
     * @param headers the response headers up to that moment
     * @param statusCode the status code designated by {@link ResponseEntityExceptionHandler}
     * @param request the request
     * @return the appropriate response
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {
        // In case of server errors, do not show detail information
        if ( statusCode.is5xxServerError() ) {
            this.strategy = CensorErrorsDetailHandlingStrategy.getInstance();
        }
        else { // In case of client errors, preserve the errors' information
            this.strategy = PreserveErrorsInformationHandlingStrategy.getInstance();
        }
        var response = this.handle(ex, HttpStatus.valueOf(statusCode.value()));
        return new ResponseEntity<>( response != null ? response.getBody() : null, headers, statusCode);
    }
}
