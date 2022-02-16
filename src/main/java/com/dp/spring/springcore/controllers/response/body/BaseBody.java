package com.dp.spring.springcore.controllers.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)      // If a field is null, it isn't shown in JSON response.
public abstract class BaseBody {
    @NonNull @Setter(AccessLevel.PRIVATE)
    private final String status;
    private Map<String, Object> data;
    private final String message;

    public BaseBody(@NonNull EStatus estatus, Map<String, Object> data, String message) {
        this.status = estatus.status();
        this.data = data;
        this.message = message;
    }

    public BaseBody(@NonNull EStatus estatus, String key, Object value, String message) {
        this.status = estatus.status();
        addDataEntry(key, value);
        this.message = message;
    }

    public void addDataEntry(String key, Object value) {
        if ( data == null ) {
            data = new HashMap<>();
        }
        data.put(key, value);
    }
}
