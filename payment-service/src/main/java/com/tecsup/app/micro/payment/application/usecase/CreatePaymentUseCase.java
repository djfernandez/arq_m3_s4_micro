package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.exception.InvalidPaymentDataException;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCase {
  private final PaymentRepository paymentRepository;

  public Payment execute(Payment payment) {
    log.debug("Executing CreatePaymentUseCase for enrollmentId: {}", payment.getEnrollmentId());
    if (!payment.isValid()) {
      throw new InvalidPaymentDataException("Invalid payment data. EnrollmentId and positive amount are required.");
    }
    payment.setStatus("PENDING");
    return paymentRepository.save(payment);
  }
}
