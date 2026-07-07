package com.tecsup.app.micro.notification.application.eventhandler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.NotificationCreatedEvent;
import com.tecsup.app.micro.notification.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationEventHandler {

  @KafkaListener(topics = KafkaConfig.NOTIFICATION_EVENT_TOPIC, // Topico que va a escuchando
      groupId = "notification-notifications-group" // Grupo de consumidores
  )
  public void handleNotificationEvents(DomainEvent event) {
    if (event instanceof NotificationCreatedEvent) {
      this.handleNotificationCreated((NotificationCreatedEvent) event);
    } else {
      throw new RuntimeException("Invalid event type " + event.getClass());
    }
  }

  public void handleNotificationCreated(NotificationCreatedEvent event) {
    log.info("[Kafka] Notification created event received: {}", event);

  }
}
