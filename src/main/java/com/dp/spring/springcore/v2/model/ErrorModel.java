package com.dp.spring.springcore.v2.model;

import com.dp.spring.springcore.v2.exceptions.Error;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * Model accompanying a HTTP response in case of any kind of error.
 * @param self      the full current request URL, that triggered the errors
 * @param status    the synthetic string representation of the response status: fail|error|generic
 * @param errors    the errors found
 */
public record ErrorModel(String self, String status, Set<Error> errors) {
    public ErrorModel(String self, HttpStatus status, Set<Error> errors) {
        this(self, ErrorModelStatus.valueOf(status), errors);
    }

    public ErrorModel(String self, String status, Error error) {
        this(self, status, Set.of(error));
    }

    public ErrorModel(String self, HttpStatus status, Error error) {
        this(self, ErrorModelStatus.valueOf(status), Set.of(error));
    }
}