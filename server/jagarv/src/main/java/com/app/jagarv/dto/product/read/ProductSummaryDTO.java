package com.app.jagarv.dto.product.read;

import java.math.BigDecimal;

public class ProductSummaryDTO {
    private Long id; 
    private String title;
    private String[] pictures; 
    private BigDecimal price;

    public Long getId() 
    { 
        return id; 
        
    }
    public void setId(Long id) 
    { 
        this.id = id;
    }

    public String getTitle() 
    { 
        return title;
    } 
    
    public void setTitle(String title) 
    {
        this.title = title; 
        
    }

    public String[] getPictures() 
    { 
        return pictures; 
        
    }
    
    public void setPictures(String[] pictures) { this.pictures = pictures; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}