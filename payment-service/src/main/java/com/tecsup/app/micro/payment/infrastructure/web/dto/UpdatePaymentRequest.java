package com.tecsup.app.micro.payment.infrastructure.web.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentRequest {

  @Pattern(regexp = "PENDING|COMPLETED|FAILED|REFUNDED", message = "Status must be PENDING, COMPLETED, FAILED or REFUNDED")
  private String status;
}
