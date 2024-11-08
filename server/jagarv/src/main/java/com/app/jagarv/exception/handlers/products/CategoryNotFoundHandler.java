package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.CategoryNotFoundException;

@ControllerAdvice 
public class CategoryNotFoundHandler {
    @ExceptionHandler(CategoryNotFoundException.class) 
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}