package com.dp.spring.springcore.model._links;

import com.fasterxml.jackson.annotation.JsonValue;

public enum _LinksRel {
    SELF("self"),
    RELATED("related"),
    PREVIOUS_PAGE("prevPage"),
    NEXT_PAGE("nextPage");

    private final String rel;

    _LinksRel(final String rel) {
        this.rel = rel;
    }

    @JsonValue
    public String rel() {
        return this.rel;
    }
}
