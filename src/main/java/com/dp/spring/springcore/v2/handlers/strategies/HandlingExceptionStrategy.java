package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Strategy interface for handling ErrorModel generation.
 * <br>
 * Existing strategies:
 * <ul>
 *     <li>{@link CensorAsInternalServerErrorHandlingStrategy}</li>
 *     <li>{@link CensorErrorsDetailHandlingStrategy}</li>
 *     <li>{@link PreserveErrorsInformationHandlingStrategy}</li>
 *     <li>{@link ValidationErrorsHandlingStrategy}</li>
 *     <li>{@link AccessDeniedHandlingStrategy}</li>
 * </ul>
 */
public interface HandlingExceptionStrategy {
    ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status);
}
