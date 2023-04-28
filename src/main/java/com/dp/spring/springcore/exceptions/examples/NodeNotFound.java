package com.dp.spring.springcore.exceptions.examples;

import com.dp.spring.springcore.exceptions.ResourceNotFoundException;
import com.dp.spring.springcore.model.error.Error;

/**
 * Example n.2. Exception extending ResourceNotFoundException.
 */
public class NodeNotFound extends ResourceNotFoundException {
    public NodeNotFound() {
        super(new Error(
                        ExceptionConstants.NODE_NOT_FOUND.getCode(),
                        ExceptionConstants.NODE_NOT_FOUND.getTitle(),
                        ExceptionConstants.NODE_NOT_FOUND.getDetail()
                )
        );
    }

    public NodeNotFound(Throwable cause) {
        super(new Error(
                        ExceptionConstants.DEVICE_NOT_FOUND.getCode(),
                        ExceptionConstants.DEVICE_NOT_FOUND.getTitle(),
                        ExceptionConstants.DEVICE_NOT_FOUND.getDetail()
                ), cause
        );
    }
}
