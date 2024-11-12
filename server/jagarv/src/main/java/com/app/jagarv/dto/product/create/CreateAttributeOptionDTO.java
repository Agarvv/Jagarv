package com.app.jagarv.dto.product.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAttributeOptionDTO {

    // Option value, like 'red', '32GB', etc.
    @NotBlank
    private String value;

    // id of the attribute that this option belongs to
    @NotNull
    private Long attributeId;

    // getters and setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }
}