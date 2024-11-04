package com.app.jagarv.exception.exceptions.users;

public class UserSessionNotValidException extends RuntimeException {
    public UserSessionNotValidException(String error) {
        super(error);
    }
}