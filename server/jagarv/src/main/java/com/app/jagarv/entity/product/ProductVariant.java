package com.app.jagarv.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_variant")
public class ProductVariant {
    
    // id of the product variant
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // new price of the product variant
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    
    // stock of the product variant
    @NotNull
    private Long stock;
   
    // product to which this variant belongs
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    // attribute options associated with this variant
   // @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<AttributeOption> attributeOptions;

    private List<String> images; // new images stored as url links in strings 


    // Getters  and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<AttributeOption> getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(List<AttributeOption> attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
