package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.exceptions.BusinessException;
import com.dp.spring.springcore.v2.exceptions.Error;
import com.dp.spring.springcore.v2.model.ErrorModel;
import com.dp.spring.springcore.v2.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Concrete handling strategy: censoring only errors' detail message but keeping errors' status, code and title.
 */
public final class CensorErrorDetailHandlingStrategy implements HandlingExceptionStrategy {
    private static CensorErrorDetailHandlingStrategy instance;

    private CensorErrorDetailHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static CensorErrorDetailHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new CensorErrorDetailHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        // If the exception is a BusinessException, status is in .getStatus() information is in .getErrors()
        if (e instanceof BusinessException be) {
            return ResponseEntity.status( be.getStatus() )
                    .body( new ErrorModel(
                            HttpUtils.getFullURIFromCurrentRequest(),
                            be.getStatus(),
                            be.getErrors()
                    ));
        }
        else { // In case of generic Exceptions, use the given status
            return ResponseEntity.status(status)
                    .body( new ErrorModel(
                            HttpUtils.getFullURIFromCurrentRequest(),
                            status,
                            new Error(
                                    null,
                                    status.getReasonPhrase(),
                                    BaseExceptionConstants.INTERNAL_SERVER_ERROR.getDetail()
                            )));
        }
    }
}
