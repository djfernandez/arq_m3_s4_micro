package com.tecsup.app.micro.payment.infrastructure.web.controller;

import com.tecsup.app.micro.payment.application.service.PaymentApplicationService;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.infrastructure.web.dto.CreatePaymentRequest;
import com.tecsup.app.micro.payment.infrastructure.web.dto.PaymentResponse;
import com.tecsup.app.micro.payment.infrastructure.web.dto.UpdatePaymentRequest;
import com.tecsup.app.micro.payment.infrastructure.web.mapper.PaymentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

  private final PaymentApplicationService paymentApplicationService;
  private final PaymentDtoMapper paymentDtoMapper;

  @GetMapping
  public ResponseEntity<List<PaymentResponse>> getAllPayments() {
    log.info("REST request to get all payments");
    return ResponseEntity.ok(paymentDtoMapper.toResponseList(paymentApplicationService.getAllPayments()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
    log.info("REST request to get payment by id: {}", id);
    return ResponseEntity.ok(paymentDtoMapper.toResponse(paymentApplicationService.getPaymentById(id)));
  }

  @PostMapping
  public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
    log.info("REST request to create payment for enrollmentId: {}", request.getEnrollmentId());
    Payment payment = paymentDtoMapper.toDomain(request);
    Payment created = paymentApplicationService.createPayment(payment);
    return ResponseEntity.status(HttpStatus.CREATED).body(paymentDtoMapper.toResponse(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaymentResponse> updatePayment(
      @PathVariable Long id,
      @Valid @RequestBody UpdatePaymentRequest request) {
    log.info("REST request to update payment with id: {}", id);
    Payment payment = paymentDtoMapper.toDomain(request);
    return ResponseEntity.ok(paymentDtoMapper.toResponse(paymentApplicationService.updatePayment(id, payment)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    log.info("REST request to delete payment with id: {}", id);
    paymentApplicationService.deletePayment(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Payment Service running with Clean Architecture!");
  }
}
