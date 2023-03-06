package com.dp.spring.springcore.v2.handlers.strategies;

import com.dp.spring.springcore.v2.exceptions.BaseExceptionConstants;
import com.dp.spring.springcore.v2.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

/**
 * Concrete handling strategy: extracting validation errors information and loading them in an appropriate ErrorModel.
 */
public final class AccessDeniedHandlingStrategy implements HandlingExceptionStrategy {
    private static AccessDeniedHandlingStrategy instance;

    private AccessDeniedHandlingStrategy() {}

    /**
     * @return the instance
     */
    public static AccessDeniedHandlingStrategy getInstance() {
        if ( instance == null ) {
            instance = new AccessDeniedHandlingStrategy();
        }
        return instance;
    }

    @Override
    public ResponseEntity<ErrorModel> handle(final Exception e, final HttpStatus status) {
        var finalStatus = (status != null) ? status : HttpStatus.FORBIDDEN;

        if ( e instanceof AccessDeniedException ) {
            return ResponseEntity.status(finalStatus)
                    .body(new ErrorModel(
                            finalStatus,
                            BaseExceptionConstants.ACCESS_DENIED.buildError()
                    ));
        }
        return null;
    }
}
