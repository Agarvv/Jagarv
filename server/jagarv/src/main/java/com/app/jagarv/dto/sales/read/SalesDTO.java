package com.app.jagarv.dto.sales.read; 

import java.math.BigDecimal;


public class SalesDTO {
    private Long id;
    private BigDecimal amount;
    private String method;
    
    public SalesDTO() {} 
    
    public SalesDTO(
     Long id,
     BigDecimal amount,
     String method
    ) {
        this.id = id;
        this.amount = amount;
        this.method = method;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
}