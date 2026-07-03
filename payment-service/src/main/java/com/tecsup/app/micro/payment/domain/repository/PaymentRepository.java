package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

  List<Payment> findAll();

  Optional<Payment> findById(Long id);

  List<Payment> findByEnrollmentId(Long enrollmentId);

  Payment save(Payment payment);

  void deleteById(Long id);
}
