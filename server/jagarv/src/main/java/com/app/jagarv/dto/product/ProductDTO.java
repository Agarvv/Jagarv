package com.app.jagarv.dto.product;

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
        this.category = category; // Asignando la categoría
        this.main_picture = main_picture;
        this.price = price;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMain_picture() {
        return main_picture;
    }

    public void setMain_picture(String main_picture) {
        this.main_picture = main_picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}