package com.app.jagarv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Arrays;
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

    // The category of the Product, like 'phones', 'pc', 'gaming' or whatever
    @NotBlank(message = "Category cannot be blank")
    private String category;

    // The Product Pictures (stored as a simple list of strings)
    @Column(name = "pictures")
    private String pictures; // Guardar como un String que representa una lista

    // The price, Not negative like -1. of course
    @DecimalMin(value = "0.00", inclusive = false, message = "The Price Can't be Negative.")
    @Digits(integer = 10, fraction = 2, message = "The Price Has Too Many Decimals.")
    private BigDecimal price;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getPictures() {
        return pictures != null ? Arrays.asList(pictures.split(",")) : null;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures != null ? String.join(",", pictures) : null;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}