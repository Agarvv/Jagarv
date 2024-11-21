package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.ProductNotPurchasedException;

// will be used when a user publishes a product opinion and that user has not purchased that product
@ControllerAdvice
public class ProductNotPurchasedHandler {
    @ExceptionHandler(ProductNotPurchasedException.class)
    public ResponseEntity<String> handleProductNotPurchasedException(ProductNotPurchasedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
