package com.dp.spring.springcore.v2.model.success.examples;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SampleDTO {
    @NotBlank
    private String stringValue;

    @Min(5) @Max(10)
    private Integer intValue;


    public SampleDTO(String stringValue, Integer intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
}
