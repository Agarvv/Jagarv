package com.app.jagarv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.math.BigDecimal;

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

    // The URL Of the Product's main picture, Meaning the picture that the user will see on the Product card.
    @URL(message = "The Main Picture Should Be a URL.")
    private String main_picture;  // <- Aquí estaba el problema, faltaba el ;

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