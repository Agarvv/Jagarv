package com.app.jagarv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    // The ID Of the product
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The title of the product, like: 'Unicorn Socks.'
    @NotBlank(message = "The Title Can't be Blank.")
    private String title;

    // The description of the product, like: 'Feel free with our Unicorn Socks!' :p
    @NotBlank(message = "The Description Can't Be Blank.")
    private String description;
    
    //The category of the Product, like 'phones', 'pc', 'gaming' or whatever
    @NotBlank(message="Category cannot be blank")
    private String category;

    // The Prouct Pictures
    @URL(message = "The Main Picture Should Be a URL.")
    private List<String> pictures;

    // The price, Not negative like -1. of course
    @DecimalMin(value = "0.00", inclusive = false, message = "The Price Can't be Negative.")
    @Digits(integer = 10, fraction = 2, message = "The Price Has Too Many Decimals.")
    private BigDecimal price;

    // Getters And setters
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