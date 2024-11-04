package com.app.jagarv.exception.exceptions.users;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String error) {
        super(error);
    }
}

// will be used on auth service, if the user email is not found.ñ