package com.app.jagarv.dto.order.read;

import com.app.jagarv.dto.product.read.OrderProductDTO;
import java.util.List;

public class OrdersDTO {
    private Long id; 
    private List<OrderProductDTO> products; 
    private String status;
    private Long amount; 
    
    public OrdersDTO() {}

    public OrdersDTO(Long id, String status, Long amount, List<OrderProductDTO> products) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
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
}