package com.tecsup.app.micro.notification.application.usecase;

import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllNotificationsUseCase {
  private final NotificationRepository notificationRepository;

  public List<Notification> execute() {
    log.debug("Executing GetAllNotificationsUseCase");
    return notificationRepository.findAll();
  }
}
