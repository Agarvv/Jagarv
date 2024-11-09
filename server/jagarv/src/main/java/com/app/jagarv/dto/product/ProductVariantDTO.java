package com.app.jagarv.dto.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantDTO {

    private Long id;
    private BigDecimal price;
    private Long stock;
    private List<String> images;
    private List<String> attributeOptions;

    public ProductVariantDTO() {
    }

    public ProductVariantDTO(Long id, BigDecimal price, Long stock, List<String> attributeOptions, List<String> images) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.attributeOptions = attributeOptions;
        this.images = images;
    }

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

    public List<String> getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(List<String> attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
 
