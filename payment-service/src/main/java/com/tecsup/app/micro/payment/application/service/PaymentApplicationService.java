package com.tecsup.app.micro.payment.application.service;

import com.tecsup.app.micro.payment.application.usecase.*;
import com.tecsup.app.micro.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentApplicationService {

  private final GetAllPaymentsUseCase getAllPaymentsUseCase;
  private final GetPaymentByIdUseCase getPaymentByIdUseCase;
  private final CreatePaymentUseCase createPaymentUseCase;
  private final UpdatePaymentUseCase updatePaymentUseCase;
  private final DeletePaymentUseCase deletePaymentUseCase;

  @Transactional(readOnly = true)
  public List<Payment> getAllPayments() {
    return getAllPaymentsUseCase.execute();
  }

  @Transactional(readOnly = true)
  public Payment getPaymentById(Long id) {
    return getPaymentByIdUseCase.execute(id);
  }

  @Transactional
  public Payment createPayment(Payment payment) {
    return createPaymentUseCase.execute(payment);
  }

  @Transactional
  public Payment updatePayment(Long id, Payment payment) {
    return updatePaymentUseCase.execute(id, payment);
  }

  @Transactional
  public void deletePayment(Long id) {
    deletePaymentUseCase.execute(id);
  }
}
