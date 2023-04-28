package com.dp.spring.springcore.model.success;

import com.dp.spring.springcore.model._links._LinksModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Body model of an HTTP response in case of successful request elaboration.
 *
 * @param <T> the domain custom DTO class
 */
public record ResourceModel<T>(
        @JsonInclude(Include.NON_NULL) _LinksModel _links,
        T data
) {
    public ResourceModel(T data) {
        this(null, data);
    }
}