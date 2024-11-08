package com.app.jagarv.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    // The ID of the product
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The title of the product, like: 'Unicorn Socks.'
    @NotBlank(message = "The Title Can't be Blank.")
    private String title;

    // The description of the product, like: 'Feel free with our Unicorn Socks!' :p
    @NotBlank(message = "The Description Can't Be Blank.")
    private String description;

    // Featured state, can be true or false
    private Boolean featured; 

    // The stock of the product, like 70, 198, or 3.
    private Long stock;

    // The creation date, Like '1/12/1765' :p
    private String date;

    // The Product Pictures (now correctly stored as a List of Strings)
    @ElementCollection
    private List<String> pictures; 

    // The price, not negative like -1. of course
    @DecimalMin(value = "0.00", inclusive = false, message = "The Price Can't be Negative.")
    @Digits(integer = 10, fraction = 2, message = "The Price Has Too Many Decimals.")
    @NotNull
    private BigDecimal price;

    // Category of the product, like 'Smartphones', 'Gaming', whatever
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    // Variants of the product, each with specific attribute combinations like color, size, etc.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariant> variants;

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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
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

    // Getter and Setter for Variants
    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }
}
