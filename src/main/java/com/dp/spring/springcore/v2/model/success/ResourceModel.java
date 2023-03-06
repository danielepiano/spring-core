package com.dp.spring.springcore.v2.model.success;

import com.dp.spring.springcore.v2.model._links._LinksModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Body model of an HTTP request including a body, or an HTTP response in case of successful request elaboration.
 * @param <T> the domain custom DTO class
 */
@Getter @Setter
public class ResourceModel<T> {
    @JsonInclude(Include.NON_NULL)
    private _LinksModel _links;

    @NonNull
    private final String type;

    private final T data;

    public ResourceModel(T data) {
        this(null, data);
    }

    public ResourceModel(_LinksModel _links, T data) {
        this._links = _links;
        this.type = data.getClass().getSimpleName();
        this.data = data;
    }
}