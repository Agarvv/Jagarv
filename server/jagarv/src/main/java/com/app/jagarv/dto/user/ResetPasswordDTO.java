package com.app.jagarv.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class ResetPasswordDTO {

    @NotBlank(message = "Token cannot be null")
    private String token; // reset password token

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Password cannot be null")
    private String password; // user new password

    public String getToken() {
        return token;
    }

    public void setToken(String resetToken) {
        this.token = resetToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setNewPassword(String newPassword) {
        this.password = newPassword;
    }
}
