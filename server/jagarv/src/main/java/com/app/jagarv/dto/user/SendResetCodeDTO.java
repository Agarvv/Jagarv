package com.app.jagarv.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class SendResetCodeDTO {
    @NotNull(message = "Email Can Not be Null")
    @Email(message = "Email not valid")
    private String email; // user email

    public String getEmail() {
        return email; 
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
