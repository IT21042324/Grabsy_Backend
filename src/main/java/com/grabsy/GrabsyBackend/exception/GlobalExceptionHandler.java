package com.grabsy.GrabsyBackend.exception;


import com.grabsy.GrabsyBackend.exception.user.UserException;
import com.grabsy.GrabsyBackend.exception.user.attribute.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handleValidationException(ValidationException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    String errorMessage = "Invalid input. Please check your request payload. For userRole, allowed values are: CUSTOMER, SELLER, ADMIN. Error details: " + ex.getMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred : " + ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred : " + ex.getMessage());
  }
}
