package com.app.jagarv.dto.user; 

import jakarta.validation.constraints.NotNull;

public class BanUserDTO 
{
    @NotNull 
    private Long userId; 
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}