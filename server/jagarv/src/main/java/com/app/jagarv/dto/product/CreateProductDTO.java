package com.app.jagarv.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.math.BigDecimal;

public class CreateProductDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Category is mandatory")
    private String category;

    
    @NotNull(message = "Pictures cannot be null") 
    @Size(min = 1, message = "At least one picture URL is required") 
    private List<String> pictures;  


    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price has too many decimals")
    private BigDecimal price;

    private Boolean featured;
    // Getters y setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
