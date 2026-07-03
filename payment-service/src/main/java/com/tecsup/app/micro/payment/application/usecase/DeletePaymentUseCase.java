package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.exception.PaymentNotFoundException;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeletePaymentUseCase {
  private final PaymentRepository paymentRepository;

  public void execute(Long id) {
    log.debug("Executing DeletePaymentUseCase for id: {}", id);
    if (!paymentRepository.findById(id).isPresent())
      throw new PaymentNotFoundException(id);
    paymentRepository.deleteById(id);
  }
}
