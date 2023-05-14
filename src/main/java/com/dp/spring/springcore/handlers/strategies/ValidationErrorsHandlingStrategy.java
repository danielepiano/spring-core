package com.dp.spring.springcore.handlers.strategies;

import com.dp.spring.springcore.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.model.error.Error;
import com.dp.spring.springcore.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                            mapValidationErrors(manve)
                    ));
        }
        return null;
    }

    /**
     * Mapping every validation error reported in the exception.
     *
     * @param e the exception where validation errors are reported
     * @return all the validation errors, each mapped as {@link Error}
     */
    private Set<Error> mapValidationErrors(final MethodArgumentNotValidException e) {
        return Stream.concat(
                mapGlobalErrors(e).stream(),
                mapFieldErrors(e).stream()
        ).collect(Collectors.toSet());
    }

    /**
     * Mapping global validation errors (i.e. constraint applied at type level).
     *
     * @param e the exception where validation errors arre reported
     * @return all the global errors, each mapped as {@link Error}
     */
    private Set<Error> mapGlobalErrors(final MethodArgumentNotValidException e) {
        return e.getGlobalErrors().stream()
                .map(this::mapGlobalError)
                .collect(Collectors.toSet());
    }

    /**
     * Mapping field validation errors (i.e. constraint applied at field level).
     *
     * @param e the exception where validation errors arre reported
     * @return all the field errors, each mapped as {@link Error}
     */
    private Set<Error> mapFieldErrors(final MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(this::mapFieldError)
                .collect(Collectors.toSet());
    }

    /**
     * Mapping a single global error.
     *
     * @param error the global error
     * @return the error mapped
     */
    private Error mapGlobalError(final ObjectError error) {
        return new Error(
                BaseExceptionConstants.VALIDATION_ERROR.getCode(),
                BaseExceptionConstants.VALIDATION_ERROR.getTitle(),
                String.format(BaseExceptionConstants.VALIDATION_ERROR.getDetail(), error.getDefaultMessage())
        );
    }

    /**
     * Mapping a single field error.
     *
     * @param error the global error
     * @return the error mapped
     */
    private Error mapFieldError(final FieldError error) {
        return new Error(
                BaseExceptionConstants.FIELD_VALIDATION_ERROR.getCode(),
                BaseExceptionConstants.FIELD_VALIDATION_ERROR.getTitle(),
                String.format(BaseExceptionConstants.FIELD_VALIDATION_ERROR.getDetail(),
                        error.getField(), error.getDefaultMessage()
                )
        );
    }
}
