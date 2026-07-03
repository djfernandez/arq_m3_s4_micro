package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllPaymentsUseCase {
  private final PaymentRepository paymentRepository;

  public List<Payment> execute() {
    log.debug("Executing GetAllPaymentsUseCase");
    return paymentRepository.findAll();
  }
}
