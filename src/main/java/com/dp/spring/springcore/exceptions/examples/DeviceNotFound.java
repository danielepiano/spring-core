package com.dp.spring.springcore.exceptions.examples;

import com.dp.spring.springcore.exceptions.ResourceNotFoundException;
import com.dp.spring.springcore.model.error.Error;

/**
 * Example n.1. Exception extending ResourceNotFoundException, requiring a parameter to fill an Error field.
 */
public class DeviceNotFound extends ResourceNotFoundException {
    public DeviceNotFound(final Integer id) {
        super(new Error(
                        ExceptionConstants.DEVICE_NOT_FOUND.getCode(),
                        ExceptionConstants.DEVICE_NOT_FOUND.getTitle(),
                        String.format(ExceptionConstants.DEVICE_NOT_FOUND.getDetail(), id)
                )
        );
    }

    public DeviceNotFound(Integer id, Throwable cause) {
        super(new Error(
                        ExceptionConstants.DEVICE_NOT_FOUND.getCode(),
                        ExceptionConstants.DEVICE_NOT_FOUND.getTitle(),
                        String.format(ExceptionConstants.DEVICE_NOT_FOUND.getDetail(), id)
                ), cause
        );
    }
}
