package com.app.jagarv.entity.product;

import com.app.jagarv.entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
public class ProductOpinion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String content; 
    
    @ManyToOne
    @JoinColumn(name="product_id")  
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="user_id")  
    private User user;
    
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getContent() 
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Product getProduct() 
    {
        return product;
    }

    public void setProduct(Product product) 
    {
        this.product = product;
    }

    public User getUser() 
    {
        return user;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }
}