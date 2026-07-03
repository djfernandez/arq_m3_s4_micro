package com.tecsup.app.micro.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
  private String method; // CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER
  private LocalDateTime paidAt;
  private LocalDateTime updatedAt;

  public boolean isValid() {
    return enrollmentId != null && enrollmentId > 0
        && amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
  }
}
