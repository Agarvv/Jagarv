package com.app.jagarv.dto.order.read;

import com.app.jagarv.dto.user.UserDTO; 

public class AdminOrderDTO {
    private Long id;
    private String status;
    private Long amount;
    private String adress;
    private UserDTO user; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    public UserDTO getUser() {
        return user; 
    }
    
    public void setUser(UserDTO user) {
        this.user = user; 
    }
}