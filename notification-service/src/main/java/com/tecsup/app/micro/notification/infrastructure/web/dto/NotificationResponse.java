package com.tecsup.app.micro.notification.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

  private Long id;
  private Long userId;
  private String type;
  private String subject;
  private String message;
  private Boolean read;
  private LocalDateTime sentAt;
  private LocalDateTime updatedAt;
}
