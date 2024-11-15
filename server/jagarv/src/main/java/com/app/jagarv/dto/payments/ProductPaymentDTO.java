package com.app.jagarv.dto.payments;

import java.util.List;

public class ProductPaymentsDTO {
    private List<Long> productIds;  // product ids
    private List<Long> optionIds;   // product options, color, size..etc

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Long> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Long> optionIds) {
        this.optionIds = optionIds;
    }
}