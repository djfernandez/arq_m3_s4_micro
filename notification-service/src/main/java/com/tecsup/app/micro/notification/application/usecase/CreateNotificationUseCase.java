package com.tecsup.app.micro.notification.application.usecase;

import com.tecsup.app.micro.notification.domain.exception.InvalidNotificationDataException;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateNotificationUseCase {
  private final NotificationRepository notificationRepository;

  public Notification execute(Notification notification) {
    log.debug("Executing CreateNotificationUseCase for userId: {}", notification.getUserId());
    if (!notification.isValid()) {
      throw new InvalidNotificationDataException("Invalid notification data. UserId, type and message are required.");
    }
    notification.setRead(false);
    return notificationRepository.save(notification);
  }
}
