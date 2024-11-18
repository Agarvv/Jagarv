package com.app.jagarv.dto.wishlist; 

import jakarta.validation.constraints.NotNull;

public class AddToWishlistDTO 
{
    @NotNull 
    private Long productId; 
    
    public Long getProductId()
    {
        return productId; 
    }
    
    public void setProductId(Long productId)
    {
        this.productId = productId; 
    }
    
}