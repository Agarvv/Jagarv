package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.AttributeNotFoundException;

@ControllerAdvice 
public class AttributeNotFoundHandler {
    @ExceptionHandler(AttributeNotFoundException.class)
    public ResponseEntity<String> 
    handleAttributeNotFoundException(AttributeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}