package com.app.jagarv.dto.product.create;

import jakarta.validation.constraints.NotNull;

public class FeatureProductDTO {
    
    @NotNull
    private Long productId;

    public Long getProductId() {
      return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}