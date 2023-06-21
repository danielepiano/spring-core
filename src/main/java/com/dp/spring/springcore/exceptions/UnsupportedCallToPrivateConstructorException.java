package com.dp.spring.springcore.exceptions;

import com.dp.spring.springcore.model.error.Error;

/**
 * Invoking the constructor of a private method should be forbidden.
 * <br>
 * E.g. you should not instantiate a utility class having a private method.
 */
public class UnsupportedCallToPrivateConstructorException extends InternalServerErrorException {
    public UnsupportedCallToPrivateConstructorException() {
        super(new Error(
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getCode(),
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getTitle(),
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getDetail()
                )
        );
    }

    public UnsupportedCallToPrivateConstructorException(Throwable cause) {
        super(new Error(
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getCode(),
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getTitle(),
                        BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getDetail()
                ), cause
        );
    }
}
