package com.app.jagarv.exception.exceptions.users;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String error) {
        super(error);
    }
}
// will be used on the AuthService, in the registerUser method 
// and will be thrown if the username already exits.