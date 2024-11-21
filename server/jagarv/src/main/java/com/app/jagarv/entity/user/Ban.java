package com.app.jagarv.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ban {

    @NotNull 
    private Long userId; 
    
    @NotBlank
    private String banDate; 
    
    @NotBlank
    private String banExpiry; 

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBanDate() {
        return banDate;
    }

    public void setBanDate(String banDate) {
        this.banDate = banDate;
    }

    public String getBanExpiry() {
        return banExpiry;
    }

    public void setBanExpiry(String banExpiry) {
        this.banExpiry = banExpiry;
    }
}