package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.exceptions.BusinessException;
import com.dp.spring.springcore.v2.model.error.Error;
import com.dp.spring.springcore.v2.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Concrete handling strategy: censoring only errors' detail message but keeping errors' status, code and title.
 */
public final class CensorErrorsDetailHandlingStrategy implements HandlingExceptionStrategy {
    private static CensorErrorsDetailHandlingStrategy instance;

    private CensorErrorsDetailHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static CensorErrorsDetailHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new CensorErrorsDetailHandlingStrategy();
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
        else { // In case of generic Exceptions, use the given status
            return ResponseEntity.status(status)
                    .body( new ErrorModel(
                            status,
                            new Error(
                                    null,
                                    status.getReasonPhrase(),
                                    BaseExceptionConstants.INTERNAL_SERVER_ERROR.getDetail()
                            )));
        }
    }
}
