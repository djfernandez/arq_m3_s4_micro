package com.tecsup.app.micro.payment.application.eventhandler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.payment.domain.event.PaymentApprovedEvent;
import com.tecsup.app.micro.payment.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.payment.shared.infrastructure.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PaymentEventHandler {

  @KafkaListener(topics = KafkaConfig.PAYMENT_EVENT_TOPIC, // Topico que va a escuchando
      groupId = "payment-notifications-group" // Grupo de consumidores
  )
  public void handlePaymentEvents(DomainEvent event) {
    if (event instanceof PaymentApprovedEvent) {
      this.handlePaymentPublished((PaymentApprovedEvent) event);
    } else {
      throw new RuntimeException("Invalid event type " + event.getClass());
    }
  }

  private void handlePaymentPublished(PaymentApprovedEvent event) {
    log.info("[Kafka] Payment published event received: {}", event);

  }

}
