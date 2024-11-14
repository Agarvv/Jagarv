package com.app.jagarv.dto.product.create;


import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDTO {
    @NotBlank(message = "Category Name Can Not Be Blank.")
    private String name; // category name 
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
