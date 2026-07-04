package com.tecsup.app.micro.enrollment.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Enrollment Domain Model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

  private Long id;
  private Long userId;
  private Long courseId;
  private String status; // ACTIVE, COMPLETED, CANCELLED
  private LocalDateTime createdAt;

  public boolean isValid() {
    return userId != null && userId > 0
        && courseId != null && courseId > 0;
  }
}
