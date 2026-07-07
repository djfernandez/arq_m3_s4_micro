package com.tecsup.app.micro.payment.application.usecase;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.payment.domain.event.PaymentApprovedEvent;
import com.tecsup.app.micro.payment.domain.event.PaymentRejectedEvent;
import com.tecsup.app.micro.payment.domain.exception.InvalidPaymentDataException;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.infrastructure.client.EnrollmentClient;
import com.tecsup.app.micro.payment.infrastructure.client.dto.EnrollmentDTO;
import com.tecsup.app.micro.payment.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCase {
  private final PaymentRepository paymentRepository;
  private final KafkaEventPublisher eventPublisher;
  private final Random random = new Random();
  private final EnrollmentClient enrollmentClient;

  public Payment execute(Payment payment) {
    log.debug("Executing CreatePaymentUseCase for enrollmentId: {}", payment.getEnrollmentId());
    if (!payment.isValid()) {
      throw new InvalidPaymentDataException("Invalid payment data. EnrollmentId and positive amount are required.");
    }

    if (this.random.nextInt(10) < 2) { // Simulate a 20% chance of failure
      payment.setStatus("CANCELLED");
      log.warn("Payment failed for enrollmentId: {}", payment.getEnrollmentId());
      PaymentRejectedEvent event = new PaymentRejectedEvent(
          payment.getId().toString(),
          payment.getEnrollmentId().toString(),
          payment.getStatus());
      log.info("Publishing PaymentRejectedEvent for paymentId: {}", payment.getId());
      eventPublisher.publish(event);
      return payment;
    }
    payment.setStatus("APPROVED");
    Payment savedPayment = paymentRepository.save(payment);
    log.info("Payment created with id: {}", savedPayment.getId());

    PaymentApprovedEvent event = new PaymentApprovedEvent(
        savedPayment.getId().toString(),
        savedPayment.getEnrollmentId().toString(),
        savedPayment.getStatus());
    log.info("Publishing PaymentApprovedEvent for paymentId: {}", savedPayment.getId());
    eventPublisher.publish(event);

    enrollmentClient.getEnrollmentById(savedPayment.getEnrollmentId(), new EnrollmentDTO("COMPLETED"));

    return savedPayment;
  }
}
