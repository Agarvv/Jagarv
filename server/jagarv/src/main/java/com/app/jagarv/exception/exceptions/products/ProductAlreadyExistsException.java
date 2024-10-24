package com.app.jagarv.exception.exceptions.products;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String error) {
        super(error);
    }
} // Will be used in the ProductService, in the /create endpoint if the product already exists.
