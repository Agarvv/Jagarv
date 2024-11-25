package com.app.jagarv.exception.handlers.discount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.discount.DiscountCodeNotFoundException; 

@ControllerAdvice 
public class DiscountCodeNotFoundHandler {
    @ExceptionHandler(DiscountCodeNotFoundException.class)
    public ResponseEntity<String> 
    handleDiscountCodeNotFoundException(DiscountCodeNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); 
    }
}