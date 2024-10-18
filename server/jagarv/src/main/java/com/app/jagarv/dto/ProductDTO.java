package com.app.jagarv.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private String category;  
    private String main_picture;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, String description, String category, String main_picture, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;  
        this.main_picture = main_picture;
        this.price = price;
    }


    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}