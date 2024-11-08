package com.app.jagarv.exception.exceptions.products;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String error) {
        super(error);
    }
}