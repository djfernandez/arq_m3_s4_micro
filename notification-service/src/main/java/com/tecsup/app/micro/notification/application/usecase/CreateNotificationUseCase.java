package com.tecsup.app.micro.notification.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.NotificationCreatedEvent;
import com.tecsup.app.micro.notification.domain.exception.InvalidNotificationDataException;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import com.tecsup.app.micro.notification.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateNotificationUseCase {
  private final NotificationRepository notificationRepository;
  private final KafkaEventPublisher eventPublisher;

  public Notification execute(Notification notification) {
    log.debug("Executing CreateNotificationUseCase for userId: {}", notification.getUserId());
    if (!notification.isValid()) {
      throw new InvalidNotificationDataException("Invalid notification data. UserId, type and message are required.");
    }

    Notification savedNotification = notificationRepository.save(notification);
    log.info("Notification created: {}", savedNotification.getId());

    NotificationCreatedEvent event = new NotificationCreatedEvent(
        savedNotification.getId(),
        savedNotification.getUserId(),
        savedNotification.getMessage());

    // Publish event
    eventPublisher.publishNotificationEvent(event);

    return savedNotification;
  }
}
