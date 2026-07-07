package com.tecsup.app.micro.notification.shared.infrastructure.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.config.KafkaConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

  private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

  public void publishCourseEvent(DomainEvent event) {

    log.info("Publishing {}", event);

    String key = event.getKey() != null ? event.getKey() : "";

    this.kafkaTemplate.send(
        KafkaConfig.COURSE_EVENT_TOPIC,
        key != null ? key : "",
        event);

  }

  public void publishNotificationEvent(DomainEvent event) {

    log.info("Publishing {}", event);

    String key = event.getKey() != null ? event.getKey() : "";

    this.kafkaTemplate.send(
        KafkaConfig.NOTIFICATION_EVENT_TOPIC,
        key != null ? key : "",
        event);

  }

  public void publishEnrollmentEvent(DomainEvent event) {

    log.info("Publishing {}", event);

    String key = event.getKey() != null ? event.getKey() : "";

    this.kafkaTemplate.send(
        KafkaConfig.ENROLLMENT_EVENT_TOPIC,
        key != null ? key : "",
        event);

  }

}
