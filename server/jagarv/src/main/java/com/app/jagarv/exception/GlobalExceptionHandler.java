package com.app.jagarv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(Exception.class)
     public ResponseEntity<String> handleGenericException(Exception ex) {
        StringWriter sw = new StringWriter();
       PrintWriter pw = new PrintWriter(sw);
      ex.printStackTrace(pw);
      String stackTrace = sw.toString();

      return new ResponseEntity<>(stackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  
}
