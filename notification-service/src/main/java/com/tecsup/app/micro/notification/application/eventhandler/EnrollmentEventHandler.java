package com.tecsup.app.micro.notification.application.eventhandler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.EnrollmentPublishedEvent;
import com.tecsup.app.micro.notification.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnrollmentEventHandler {

  @KafkaListener(topics = KafkaConfig.ENROLLMENT_EVENT_TOPIC, // Topico que va a escuchando
      groupId = "enrollment-notifications-group" // Grupo de consumidores
  )
  public void handleEnrollmentEvents(DomainEvent event) {
    if (event instanceof EnrollmentPublishedEvent) {
      this.handleEnrollmentPublished((EnrollmentPublishedEvent) event);
    } else {
      throw new RuntimeException("Invalid event type " + event.getClass());
    }
  }

  private void handleEnrollmentPublished(EnrollmentPublishedEvent event) {
    log.info("[Kafka] Enrollment published event received: {}", event);

  }

}
