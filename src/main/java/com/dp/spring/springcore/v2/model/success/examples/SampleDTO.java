package com.dp.spring.springcore.v2.model.success.examples;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
