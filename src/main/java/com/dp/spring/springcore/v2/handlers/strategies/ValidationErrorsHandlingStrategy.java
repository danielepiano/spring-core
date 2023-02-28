package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.exceptions.Error;
import com.dp.spring.springcore.v2.model.ErrorModel;
import com.dp.spring.springcore.v2.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

/**
 * Concrete handling strategy: extracting validation errors information and loading them in an appropriate ErrorModel.
 */
public final class ValidationErrorsHandlingStrategy implements HandlingExceptionStrategy {
    private static ValidationErrorsHandlingStrategy instance;

    private ValidationErrorsHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static ValidationErrorsHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new ValidationErrorsHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        var finalStatus = (status != null) ? status : HttpStatus.BAD_REQUEST;

        if ( e instanceof MethodArgumentNotValidException manve ) {
            return ResponseEntity.status(finalStatus)
                    .body( new ErrorModel(
                            HttpUtils.getFullURIFromCurrentRequest(),
                            finalStatus,
                            manve.getFieldErrors().stream()
                                    .map(error -> new Error(
                                            BaseExceptionConstants.VALIDATION_EXCEPTION.getCode(),
                                            BaseExceptionConstants.VALIDATION_EXCEPTION.getTitle(),
                                            String.format( BaseExceptionConstants.VALIDATION_EXCEPTION.getDetail(),
                                                    error.getField(), error.getDefaultMessage())
                                    )).collect(Collectors.toSet())
                    ));
        }
        return null;
    }
}
