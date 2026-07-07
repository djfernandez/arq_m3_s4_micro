package com.tecsup.app.micro.payment.infrastructure.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.infrastructure.web.dto.CreatePaymentRequest;
import com.tecsup.app.micro.payment.infrastructure.web.dto.PaymentResponse;
import com.tecsup.app.micro.payment.infrastructure.web.dto.UpdatePaymentRequest;

@Mapper(componentModel = "spring")
public interface PaymentDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "paidAt", ignore = true)
  Payment toDomain(CreatePaymentRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enrollmentId", ignore = true)
  @Mapping(target = "paidAt", ignore = true)
  @Mapping(target = "amount", ignore = true)
  Payment toDomain(UpdatePaymentRequest request);

  PaymentResponse toResponse(Payment payment);

  List<PaymentResponse> toResponseList(List<Payment> payments);
}
