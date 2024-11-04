package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.ProductAlreadyExistsException;

// This class handles exceptions related to resources that already exist in the application.
// It centralizes error management for resources such as products, users, etc.
@ControllerAdvice
public class ProductAlreadyExistsHandler {

    // Handles the exception when an attempt is made to create a product that already exists.
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        // Returns a response with a 409 Conflict status and a message indicating the conflict.
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}