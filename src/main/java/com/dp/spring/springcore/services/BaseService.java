package com.dp.spring.springcore.services;

import org.springframework.lang.NonNull;

/**
 * Base service to extend in order to implement business services common methods.
 */
public abstract class BaseService {
    protected String likeLR(@NonNull String s) {
        return "%" + s + "%";
    }

    protected String likeL(@NonNull String s) {
        return "%" + s;
    }

    protected String likeR(@NonNull String s) {
        return s + "%";
    }
}