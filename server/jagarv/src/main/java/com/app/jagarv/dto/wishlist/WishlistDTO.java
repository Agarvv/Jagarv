package com.app.jagarv.dto.wishlist; 

import com.app.jagarv.entity.product.Product; 


public class WishlistDTO 
{
    private Long id; 
    private List<Product> products; 
    
    public Long getId() 
    {
        return id; 
    }
    
    public void setId(Long id) 
    {
        this.id = id; 
    }
    
    public List<Product> getProducts() 
    {
        return products; 
    }
    
    public void setProducts(List<Product> products)
    {
        this.products = products; 
    }
}