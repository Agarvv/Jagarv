package com.app.jagarv.exception.exceptions.products; 

public class AttributeAlreadyExistsException extends RuntimeException {
    public AttributeAlreadyExistsException(String error) {
        super(error);
    }
}