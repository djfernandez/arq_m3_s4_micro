package com.tecsup.app.micro.payment.shared.infrastructure.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.payment.domain.event.PaymentApprovedEvent;
import com.tecsup.app.micro.payment.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.payment.shared.infrastructure.config.KafkaConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {
  private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

  public void publish(DomainEvent event) {

    log.info("Publishing {}", event);

    String topic = getTopicFromEvent(event);

    String key = event.getKey();

    //

    if (topic != null && key != null) {
      this.kafkaTemplate.send(
          topic,
          key,
          event);
    }

  }

  private String getTopicFromEvent(DomainEvent event) {

    if (event instanceof PaymentApprovedEvent) {
      return KafkaConfig.PAYMENT_EVENT_TOPIC;
    } else {
      throw new IllegalArgumentException("Unknown event type: " +
          event.getEventType());
    }
  }
}
