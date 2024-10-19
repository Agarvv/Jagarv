package com.app.jagarv.dto.order;

public class OrdersDTO {
    private String status;
    private Long quantity;
    
    public OrdersDTO() {} 

    
    public OrdersDTO(String status, Long quantity)
    {
        this.status = status;
        this.quantity = quantity;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    
}
