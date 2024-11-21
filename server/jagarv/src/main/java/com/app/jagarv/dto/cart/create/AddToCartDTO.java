package com.app.jagarv.dto.cart.create;


import java.util.List;

import jakarta.validation.constraints.NotNull;

public class AddToCartDTO {
    @NotNull
    private Long productId;
    private List<Long> options; // the product options, like color, size or whatever
    private Long quantity; 

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getOptions() {
        return options;
    }

    public void setOptions(List<Long> options) {
        this.options = options;
    }
    
    public Long getQuantity() {
        return quantity; 
    }
    
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
