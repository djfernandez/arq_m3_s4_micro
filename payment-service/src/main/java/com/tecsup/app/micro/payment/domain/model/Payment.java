package com.tecsup.app.micro.payment.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payment Domain Model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  private Long id;
  private Long enrollmentId;
  private BigDecimal amount;
  private String status; // PENDING, COMPLETED, FAILED, REFUNDED
  private LocalDateTime paidAt;

  public boolean isValid() {
    return enrollmentId != null && enrollmentId > 0
        && amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
  }
}
