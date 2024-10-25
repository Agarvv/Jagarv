package com.app.jagarv.dto.product;

import java.util.List;
import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private String category; 
    private Boolean featured;
    private List<String> pictures;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, String description, String category, List<String> pictures, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category; 
        this.pictures = pictures;
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