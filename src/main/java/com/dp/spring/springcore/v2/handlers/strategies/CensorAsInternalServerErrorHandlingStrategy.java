package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Concrete handling strategy: censoring errors and returning an Internal Server Error.
 */
public final class CensorAsInternalServerErrorHandlingStrategy implements HandlingExceptionStrategy {
    private static CensorAsInternalServerErrorHandlingStrategy instance;

    private CensorAsInternalServerErrorHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static CensorAsInternalServerErrorHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new CensorAsInternalServerErrorHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        var finalStatus = (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(finalStatus)
                .body( new ErrorModel(
                        finalStatus,
                        BaseExceptionConstants.INTERNAL_SERVER_ERROR.buildError()
                ));
    }
}
