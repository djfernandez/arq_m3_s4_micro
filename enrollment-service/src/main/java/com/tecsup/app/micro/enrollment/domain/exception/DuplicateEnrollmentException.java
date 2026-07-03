package com.tecsup.app.micro.enrollment.domain.exception;

public class DuplicateEnrollmentException extends RuntimeException {
  public DuplicateEnrollmentException(Long userId, Long courseId) {
    super("User " + userId + " is already enrolled in course " + courseId);
  }
}
