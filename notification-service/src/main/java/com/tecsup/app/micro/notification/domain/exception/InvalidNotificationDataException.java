package com.tecsup.app.micro.notification.domain.exception;

public class InvalidNotificationDataException extends RuntimeException {
  public InvalidNotificationDataException(String message) {
    super(message);
  }
}
