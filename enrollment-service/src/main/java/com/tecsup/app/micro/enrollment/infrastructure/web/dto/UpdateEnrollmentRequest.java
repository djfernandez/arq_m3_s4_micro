package com.tecsup.app.micro.enrollment.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEnrollmentRequest {

  @NotBlank(message = "Status is required")
  @Pattern(regexp = "ACTIVE|COMPLETED|CANCELLED|PENDING_PAYMENT", message = "Status must be ACTIVE, COMPLETED, CANCELLED or PENDING_PAYMENT")
  private String status;
}
