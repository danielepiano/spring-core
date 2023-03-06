package com.dp.spring.springcore.v2.exceptions;

import com.dp.spring.springcore.v2.model.error.Error;

/**
 * Invoking the constructor of a private method should be forbidden.
 * <br>
 * E.g. you should not instantiate a utility class having a private method.
 */
public class UnsupportedCallToPrivateConstructor extends InternalServerErrorException {
    public UnsupportedCallToPrivateConstructor() {
        super(new Error(
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getCode(),
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getTitle(),
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getDetail()
                )
        );
    }

    public UnsupportedCallToPrivateConstructor(Throwable cause) {
        super(new Error(
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getCode(),
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getTitle(),
                BaseExceptionConstants.UNSUPPORTED_CALL_TO_PRIVATE_CONSTRUCTOR.getDetail()
                ), cause
        );
    }
}
