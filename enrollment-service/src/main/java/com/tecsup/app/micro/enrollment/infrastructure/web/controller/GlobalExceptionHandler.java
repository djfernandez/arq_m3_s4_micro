package com.tecsup.app.micro.enrollment.infrastructure.web.controller;

import com.tecsup.app.micro.enrollment.domain.exception.DuplicateEnrollmentException;
import com.tecsup.app.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import com.tecsup.app.micro.enrollment.domain.exception.InvalidEnrollmentDataException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(EnrollmentNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(EnrollmentNotFoundException ex) {
    log.error("Enrollment not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(DuplicateEnrollmentException.class)
  public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateEnrollmentException ex) {
    log.error("Duplicate enrollment: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(InvalidEnrollmentDataException.class)
  public ResponseEntity<ErrorResponse> handleInvalid(InvalidEnrollmentDataException ex) {
    log.error("Invalid enrollment data: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      errors.put(fieldName, error.getDefaultMessage());
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors,
            LocalDateTime.now()));
  }

  @Data
  @AllArgsConstructor
  public static class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
  }

  @Data
  @AllArgsConstructor
  public static class ValidationErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
  }
}
