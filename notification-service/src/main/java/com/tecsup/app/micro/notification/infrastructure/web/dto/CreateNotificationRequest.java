package com.tecsup.app.micro.notification.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {

  @NotNull(message = "UserId is required")
  @Positive(message = "UserId must be positive")
  private Long userId;

  @NotBlank(message = "Type is required")
  @Pattern(regexp = "EMAIL|SMS|PUSH", message = "Type must be EMAIL, SMS or PUSH")
  private String type;

  @Size(max = 200, message = "Subject must not exceed 200 characters")
  private String subject;

  @NotBlank(message = "Message is required")
  @Size(max = 1000, message = "Message must not exceed 1000 characters")
  private String message;
}
