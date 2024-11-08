package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.CategoryAlreadyExistsException; 

@ControllerAdvice 
public class CategoryAlreadyExistsHandler {
    
    @ExceptionHandler(CategoryAlreadyExistsException.class) 
    public ResponseEntity<String> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}