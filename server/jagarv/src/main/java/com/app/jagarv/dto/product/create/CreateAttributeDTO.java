package com.app.jagarv.dto.product.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAttributeDTO {

    // Name of attribute, like color, size, ram, etc.
    @NotBlank
    private String name;
    
    // category id this attribute belongs to
    @NotNull
    private Long categoryId;

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}