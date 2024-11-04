package com.app.jagarv.exception.exceptions.users;


public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String error) {
        super(error);
    }
}