package com.tecsup.app.micro.payment.infrastructure.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

  @NotNull(message = "EnrollmentId is required")
  @Positive(message = "EnrollmentId must be positive")
  private Long enrollmentId;

  @NotNull(message = "Amount is required")
  @DecimalMin(value = "0.01", message = "Amount must be positive")
  private BigDecimal amount;

  private String method;
}
