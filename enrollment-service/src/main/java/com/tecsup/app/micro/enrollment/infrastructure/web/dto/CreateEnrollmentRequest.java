package com.tecsup.app.micro.enrollment.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnrollmentRequest {

  @NotNull(message = "UserId is required")
  @Positive(message = "UserId must be positive")
  private Long userId;

  @NotNull(message = "CourseId is required")
  @Positive(message = "CourseId must be positive")
  private Long courseId;
}
