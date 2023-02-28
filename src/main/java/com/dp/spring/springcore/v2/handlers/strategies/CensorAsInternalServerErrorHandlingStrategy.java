package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.model.ErrorModel;
import com.dp.spring.springcore.v2.utils.HttpUtils;
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body( new ErrorModel(
                        HttpUtils.getFullURIFromCurrentRequest(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        BaseExceptionConstants.INTERNAL_SERVER_ERROR.buildError()
                ));
    }
}
