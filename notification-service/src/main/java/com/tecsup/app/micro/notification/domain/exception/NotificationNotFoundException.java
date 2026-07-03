package com.tecsup.app.micro.notification.domain.exception;

public class NotificationNotFoundException extends RuntimeException {
  public NotificationNotFoundException(Long id) {
    super("Notification not found with id: " + id);
  }
}
