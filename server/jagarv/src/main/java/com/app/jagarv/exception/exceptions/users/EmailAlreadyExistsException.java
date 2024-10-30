package com.app.jagarv.exception.exceptions.users;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String error) {
        super(error);
    }
}
// will be used on the AuthService, in the registerUser method
// and will be thrown if the email already exits.