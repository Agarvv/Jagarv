package com.app.jagarv.dto.user; 

import jakarta.validation.constraints.NotBlank;

public class SetUserAdressDTO {
    @NotBlank
    private String adress; 
    
    public String getAdress() {
        return adress; 
    }
    
    public void setAdress(String adress) {
        this.adress = adress; 
    }
}