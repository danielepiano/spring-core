package com.dp.spring.springcore.controllers.exceptions;

import com.dp.spring.springcore.controllers.response.BaseResponse;
import com.dp.spring.springcore.controllers.response.ErrorResponse;
import com.dp.spring.springcore.controllers.response.FailResponse;
import com.dp.spring.springcore.controllers.response.body.ErrorBody;
import com.dp.spring.springcore.controllers.response.body.FailBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic definition of exception handlers.
 * !    Set spring.mvc.throw-exception-if-no-handler-found=true in application.properties.
 * !    Create a @ControllerAdvice ConcreteExceptionHandler extends BaseExceptionHandler.
 */
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public BaseResponse handleGenericException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    public BaseResponse handleAccessDeniedException(AccessDeniedException e) {
        return new FailResponse("unauthorized", "Access denied.");
    }

    @ExceptionHandler
    public BaseResponse handleItemNotFoundException(ItemNotFoundException e) {
        return new FailResponse(e.getFailurePoint(), e.getDetails());
    }

    @ExceptionHandler
    public BaseResponse handleNoDataFoundException(NoDataFoundException e) {
        return new FailResponse(e.getFailurePoint(), e.getDetails());
    }

    /**
     * Overridden method that handles MethodArgumentNotValidException, aka validation errors.
     * A FailBody response is sent, with validation errors in the data field.
     * @param ex        To handle exception.
     * @param headers   Headers of the response to send.
     * @param status    Status of the response to send.
     * @param request   Request.
     * @return          ResponseEntity to send.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> failures = new HashMap<>();
        ex.getAllErrors().forEach( error -> failures.put( ((FieldError)error).getField(), error.getDefaultMessage() ) );
        return new ResponseEntity<>(new FailBody(failures), headers, status);
    }

    /**
     * The supermethod is finally to used by Spring to handle http request exceptions.
     * We force the body to be our BaseBody (FailBody for 4XX statuses; ErrorBody for 5XX statuses), then recall the
     * supermethod.
     * @param ex        To handle exception.
     * @param body      Body of the response to send.
     * @param headers   Headers of the response to send.
     * @param status    Status of the response to send.
     * @param request   Request.
     * @return          ResponseEntity to send.
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex,
                status.value() < 500 ?
                        new FailBody("failure", ex.getMessage()) :
                        new ErrorBody(ex.getMessage()),
                headers, status, request);
    }
}
