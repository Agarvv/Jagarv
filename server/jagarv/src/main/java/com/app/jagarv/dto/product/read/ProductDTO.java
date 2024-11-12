package com.app.jagarv.dto.product.read;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class ProductDTO {

    private Long id;
    private String title;
    private String description;
    private ProductCategoryDTO category;  
    private Boolean featured;
    private Long stock;
    private LocalDate date; 
    private List<String> pictures;
    private BigDecimal price;
    private List<ProductVariantDTO> variants;  

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, String description, ProductCategoryDTO category, Boolean featured, Long stock, LocalDate date, List<String> pictures, BigDecimal price, List<ProductVariantDTO> variants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.featured = featured;
        this.stock = stock;
        this.date = date;
        this.pictures = pictures;
        this.price = price;
        this.variants = variants;
    }

    // Getters and Setters

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

    public ProductCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryDTO category) {
        this.category = category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public List<ProductVariantDTO> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariantDTO> variants) {
        this.variants = variants;
    }
}
