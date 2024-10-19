package com.app.jagarv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//THIS HANDLER WILL HANDLE OTHER TYPE OF EXCEPTIONS THAT OUR EXCEPTIONS FILES HANDLERS DONT.
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Something went wrong. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}

