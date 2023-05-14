package com.dp.spring.springcore.handlers.strategies;

import com.dp.spring.springcore.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.model.error.Error;
import com.dp.spring.springcore.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

/**
 * Concrete handling strategy: extracting validation errors information and loading them in an appropriate ErrorModel.
 */
public final class ValidationErrorsHandlingStrategy implements HandlingExceptionStrategy {
    private static ValidationErrorsHandlingStrategy instance;

    private ValidationErrorsHandlingStrategy() {
    }

    /**
     * @return the instance
     */
    public static ValidationErrorsHandlingStrategy getInstance() {
        if (instance == null) {
            instance = new ValidationErrorsHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        var finalStatus = (status != null) ? status : HttpStatus.BAD_REQUEST;

        if (e instanceof MethodArgumentNotValidException manve) {
            return ResponseEntity.status(finalStatus)
                    .body(new ErrorModel(
                            finalStatus,
                            manve.getGlobalErrors().stream()
                                    .map(this::mapToError)
                                    .collect(Collectors.toSet())
                    ));
        }
        return null;
    }

    private Error mapToError(ObjectError error) {
        if (error instanceof FieldError) {
            return new Error(
                    BaseExceptionConstants.FIELD_VALIDATION_ERROR.getCode(),
                    BaseExceptionConstants.FIELD_VALIDATION_ERROR.getTitle(),
                    String.format(BaseExceptionConstants.FIELD_VALIDATION_ERROR.getDetail(),
                            ((FieldError) error).getField(), error.getDefaultMessage()
                    )
            );
        }
        return new Error(
                BaseExceptionConstants.VALIDATION_ERROR.getCode(),
                BaseExceptionConstants.VALIDATION_ERROR.getTitle(),
                String.format(BaseExceptionConstants.VALIDATION_ERROR.getDetail(), error.getDefaultMessage())
        );
    }
}
