package com.app.jagarv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
@Table(name = "orders") 
public class Order {
    
    // THE ORDER ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // THE ORDER PRODUCT ID 
    @NotNull(message = "Product ID cannot be null") 
    private Long productId;

    // THE ORDER USER ID
    @NotNull(message = "User ID cannot be null") 
    private Long userId;

    // THE ORDER STATUS
    @NotBlank(message = "Status cannot be blank") 
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    private String status;
    
    // HOW MANY PRODUCTS THE USER ORDERED, LIKE 3 or 1 or 5 or even 5453123416, whatever.
    @Min(value = 1, message = "Quantity must be at least 1") 
    private Long quantity;

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
