package com.app.jagarv.dto.cart.read;

import java.util.List;
import java.math.BigDecimal; 


public class CartItemDTO {

    private Long productId;
    private String title;
    private BigDecimal price; 
    private Long quantity;
    private List<Long> options;

    public CartItemDTO(Long productId, String productName, Long quantity, List<Long> options) {
        this.productId = productId;
        this.title  = productName;
        this.quantity = quantity;
        this.options = options;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<Long> getOptions() {
        return options;
    }

    public void setOptions(List<Long> options) {
        this.options = options;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}