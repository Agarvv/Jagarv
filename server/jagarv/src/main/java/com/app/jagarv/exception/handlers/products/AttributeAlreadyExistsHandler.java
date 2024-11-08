package com.app.jagarv.exception.handlers.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.products.AttributeAlreadyExistsException;

@ControllerAdvice 
public class AttributeAlreadyExistsHandler {
    @ExceptionHandler(AttributeAlreadyExistsException.class) 
    public ResponseEntity<String> handleAttributeAlreadyExistsException(AttributeAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}