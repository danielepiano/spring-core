package com.dp.spring.springcore.handlers.strategies;

import com.dp.spring.springcore.exceptions.BusinessException;
import com.dp.spring.springcore.model.error.Error;
import com.dp.spring.springcore.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Concrete handling strategy: censoring only errors' detail message but keeping errors' status, code and title.
 */
public final class PreserveErrorsInformationHandlingStrategy implements HandlingExceptionStrategy {
    private static PreserveErrorsInformationHandlingStrategy instance;

    private PreserveErrorsInformationHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static PreserveErrorsInformationHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new PreserveErrorsInformationHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        // In case of a BusinessException, status is in .getStatus() information is in .getErrors()
        if (e instanceof BusinessException be) {
            return ResponseEntity.status( be.getStatus() )
                    .body( new ErrorModel(
                            be.getStatus(),
                            be.getErrors()
                            ));
        }
        else { // In case of generic Exceptions, deduce title from status and detail from exception message
            return ResponseEntity.status(status)
                    .body( new ErrorModel(
                            status,
                            new Error(null, status.getReasonPhrase(), e.getMessage())
                    ));
        }
    }
}
