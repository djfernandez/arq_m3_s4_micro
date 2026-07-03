package com.tecsup.app.micro.enrollment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
  private LocalDateTime enrolledAt;
  private LocalDateTime updatedAt;

  public boolean isValid() {
    return userId != null && userId > 0
        && courseId != null && courseId > 0;
  }
}
