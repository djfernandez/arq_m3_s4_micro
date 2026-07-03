package com.tecsup.app.micro.notification.application.usecase;

import com.tecsup.app.micro.notification.domain.exception.NotificationNotFoundException;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MarkNotificationReadUseCase {
  private final NotificationRepository notificationRepository;

  public Notification execute(Long id) {
    log.debug("Executing MarkNotificationReadUseCase for id: {}", id);
    Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException(id));
    notification.setRead(true);
    return notificationRepository.save(notification);
  }
}
