package com.app.jagarv.dto.product.create;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class CreateProductVariantDTO {
    
    // the original product ID
    @NotNull
    private Long productId;
    
    // the new product price.
    private BigDecimal price;
    
    // the product stock with his options 
    // like the variant Iphone 15 pro max, color red has 30 in stock.
    private Long stock;
    
    // the variant options 
    private List<Long> optionIds; 
    
    // the variant new images.
    private List<String> images;

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public List<Long> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Long> optionIds) {
        this.optionIds = optionIds;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
}