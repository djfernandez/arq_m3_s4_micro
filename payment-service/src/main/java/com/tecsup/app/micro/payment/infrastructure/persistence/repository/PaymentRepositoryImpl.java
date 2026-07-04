package com.tecsup.app.micro.payment.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.infrastructure.persistence.entity.PaymentEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {

  private final JpaPaymentRepository jpaPaymentRepository;

  @Override
  public List<Payment> findAll() {
    return jpaPaymentRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Optional<Payment> findById(Long id) {
    return jpaPaymentRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<Payment> findByEnrollmentId(Long enrollmentId) {
    return jpaPaymentRepository.findByEnrollmentId(enrollmentId).stream().map(this::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Payment save(Payment payment) {
    return toDomain(jpaPaymentRepository.save(toEntity(payment)));
  }

  @Override
  public void deleteById(Long id) {
    jpaPaymentRepository.deleteById(id);
  }

  private Payment toDomain(PaymentEntity entity) {
    return Payment.builder()
        .id(entity.getId())
        .enrollmentId(entity.getEnrollmentId())
        .amount(entity.getAmount())
        .status(entity.getStatus())
        .paidAt(entity.getPaidAt())
        .build();
  }

  private PaymentEntity toEntity(Payment payment) {
    return PaymentEntity.builder()
        .id(payment.getId())
        .enrollmentId(payment.getEnrollmentId())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .build();
  }
}
