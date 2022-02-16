package com.dp.spring.springcore.services;

import lombok.NonNull;

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