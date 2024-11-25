package com.app.jagarv.dto.payments;

import java.util.List;

public class ProductPaymentDTO {
    private String discountCode; // optional
    
    public String getDiscountCode() 
    {
        return discountCode; 
    }
    
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode; 
    }
}