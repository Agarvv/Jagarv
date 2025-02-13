package com.app.jagarv.dto.order.read;

import com.app.jagarv.dto.product.read.OrderProductDTO;
import java.util.List;
import com.app.jagarv.entity.cart.CartItem; 

public class OrdersDTO {
    private Long id; 
    private List<CartItem> products; 
    private String status;
    private Long amount; 
    private String date;
    
    public OrdersDTO() {}

    public OrdersDTO(Long id, String status, Long amount, List<CartItem> products, String date) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.products = products;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}