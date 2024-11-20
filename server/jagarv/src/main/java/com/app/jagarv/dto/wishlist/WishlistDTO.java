package com.app.jagarv.dto.wishlist; 

import java.util.List;

import com.app.jagarv.dto.product.read.ProductDTO;
import com.app.jagarv.entity.product.Product; 


public class WishlistDTO 
{
    private Long id; 
    private List<ProductDTO> products; 
    
    public Long getId() 
    {
        return id; 
    }
    
    public void setId(Long id) 
    {
        this.id = id; 
    }
    
    public List<ProductDTO> getProducts() 
    {
        return products; 
    }
    
    public void setProducts(List<ProductDTO> products)
    {
        this.products = products; 
    }
}