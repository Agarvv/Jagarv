package com.app.jagarv.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.app.jagarv.entity.product.ProductCategory;

import java.math.BigDecimal;

public class CreateProductDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Category is mandatory")
    private Long category;

    @NotNull(message = "Pictures cannot be null") 
    @Size(min = 1, message = "At least one picture is required") 
    private String[] pictures;  

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price has too many decimals")
    private BigDecimal price;

    private Boolean featured;  

    private Long stock;  
    
    private String date;  

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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) { 
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getFeatured() {  
        return featured;
    }

    public void setFeatured(Boolean featured) {  // Agregado
        this.featured = featured;
    }

    public Long getStock() {  
        return stock;
    }

    public void setStock(Long stock) {  
        this.stock = stock;
    }
    
    public String getDate() {  
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}