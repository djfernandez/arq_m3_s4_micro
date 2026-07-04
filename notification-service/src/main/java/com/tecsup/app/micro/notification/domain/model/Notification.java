package com.tecsup.app.micro.notification.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String message;
  private Boolean sent;
  private LocalDateTime createdAt;

  public boolean isValid() {
    return userId != null && userId > 0
        && message != null && !message.trim().isEmpty();
  }
}
