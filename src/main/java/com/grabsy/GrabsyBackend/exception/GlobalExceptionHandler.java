package com.grabsy.GrabsyBackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UserException.class)
  public ResponseEntity<String> handleUserException(UserException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
