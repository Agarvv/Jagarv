package com.app.jagarv.dto.product;
import com.app.jagarv.entity.product.ProductCategory;

import java.util.List;
import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private ProductCategory  category; 
    private Boolean featured;
    private Long stock;  
    private String date; 
    private List<String> pictures;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, String description, ProductCategory category, Boolean featured, Long stock, String date, List<String> pictures, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category; 
        this.featured = featured;  
        this.stock = stock;  
        this.date = date; 
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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