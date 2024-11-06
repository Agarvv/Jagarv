package com.app.jagarv.dto.user;

import jakarta.validation.constraints.NotBlank;


public class GoogleAuthDTO {
    @NotBlank
    private String googleAuthToken; // the token provided by google to handle authentication

    public String getGoogleAuthToken() {
        return googleAuthToken;
    }

    public void setGoogleAuthToken(String token) {
        this.googleAuthToken = token;
    }
}
