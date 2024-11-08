package com.app.jagarv.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "product_category") 
public class ProductCategory {
    
    // category id
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    
    // category name like 'Smartphones', 'Laptops', 'Gaming', whatever.
    @NotBlank
    private String name;
    
    // product list with the category
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) 
    private List<Product> products;
    
    // getters & setters
    public Long getCategoryId() {
        return category_id;
    }

    public void setId(Long id) {
        this.category_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
