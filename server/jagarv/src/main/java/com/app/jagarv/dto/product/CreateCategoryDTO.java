package com.app.jagarv.dto.product.CreateCategoryDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
