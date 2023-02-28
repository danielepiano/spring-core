package com.dp.spring.springcore.v2.handlers;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.exceptions.BusinessException;
import com.dp.spring.springcore.v2.exceptions.Error;
import com.dp.spring.springcore.v2.model.ErrorModel;
import com.dp.spring.springcore.v2.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Basic exceptions handler definition.
 * <br>
 * If an exception occurs due to a server error, the related information is omitted for security reasons.
 * <br><br>
 * ! In order to use this configuration, you have to
 * <ul>
 *     <li>Set:        spring.mvc.throw-exception-if-no-handler-found=true in application.properties.</li>
 *     <li>Create:     @ControllerAdvice ConcreteExceptionHandler extends BaseExceptionHandler.</li>
 * </ul>
 */
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Generic Exceptions will result in an undefined Internal Server Error.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorModel(
                        HttpUtils.getFullURIFromCurrentRequest(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        BaseExceptionConstants.INTERNAL_SERVER_ERROR.buildError()
                ));
    }

    /**
     * Business Exceptions information will be censored if due to server errors.
     * <br>
     * Any error information can still be read through console logs.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {
        return ResponseEntity.status( e.getStatus() )
                .body( e.getStatus().is5xxServerError() ?
                        new ErrorModel(
                                HttpUtils.getFullURIFromCurrentRequest(),
                                e.getStatus(),
                                BaseExceptionConstants.INTERNAL_SERVER_ERROR.buildError()
                        ) :
                        new ErrorModel(
                                HttpUtils.getFullURIFromCurrentRequest(),
                                e.getStatus(),
                                e.getErrors()
                        ));
    }

    /**
     * AccessDeniedExceptions are typically triggered when an authenticated user attempts to access a resource or
     * perform an operation for which they do not have sufficient privileges or permissions.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorModel(
                        HttpUtils.getFullURIFromCurrentRequest(),
                        HttpStatus.FORBIDDEN,
                        BaseExceptionConstants.ACCESS_DENIED.buildError()
                ));
    }

    /**
     * MethodArgumentNotValidExceptions are typically triggered when there are validation errors in the arguments
     * of a method in a Spring MVC controller.
     * fields.
     * @param e the exception to handle
     * @return the appropriate response
     */
    @ExceptionHandler
    protected ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorModel(
                        HttpUtils.getFullURIFromCurrentRequest(),
                        HttpStatus.BAD_REQUEST,
                        e.getFieldErrors().stream()
                                .map(error -> new Error(
                                        BaseExceptionConstants.VALIDATION_EXCEPTION.getCode(),
                                        BaseExceptionConstants.VALIDATION_EXCEPTION.getTitle(),
                                        String.format( BaseExceptionConstants.VALIDATION_EXCEPTION.getDetail(),
                                                error.getField(), error.getDefaultMessage())
                                )).collect(Collectors.toSet())
                ));
    }
}
