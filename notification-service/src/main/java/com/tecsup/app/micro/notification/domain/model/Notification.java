package com.tecsup.app.micro.notification.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Notification Domain Model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

  private Long id;
  private Long userId;
  private String type; // EMAIL, SMS, PUSH
  private String subject;
  private String message;
  private Boolean read;
  private LocalDateTime sentAt;
  private LocalDateTime updatedAt;

  public boolean isValid() {
    return userId != null && userId > 0
        && message != null && !message.trim().isEmpty()
        && type != null && !type.trim().isEmpty();
  }
}
