package com.tecsup.app.micro.course.domain.exception;

public class DuplicateCourseException extends RuntimeException {

  public DuplicateCourseException(String title) {
    super("Course already exists with title: " + title);
  }
}
