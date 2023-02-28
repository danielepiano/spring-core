package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BusinessException;
import com.dp.spring.springcore.v2.exceptions.Error;
import com.dp.spring.springcore.v2.model.ErrorModel;
import com.dp.spring.springcore.v2.utils.HttpUtils;
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
                            HttpUtils.getFullURIFromCurrentRequest(),
                            be.getStatus(),
                            be.getErrors()
                            ));
        }
        else { // In case of generic Exceptions, deduce title from status and detail from exception message
            return ResponseEntity.status(status)
                    .body( new ErrorModel(
                            HttpUtils.getFullURIFromCurrentRequest(),
                            status,
                            new Error(null, status.getReasonPhrase(), e.getMessage())
                    ));
        }
    }
}
