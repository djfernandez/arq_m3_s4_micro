package com.tecsup.app.micro.notification.application.usecase;

import com.tecsup.app.micro.notification.domain.exception.NotificationNotFoundException;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteNotificationUseCase {
  private final NotificationRepository notificationRepository;

  public void execute(Long id) {
    log.debug("Executing DeleteNotificationUseCase for id: {}", id);
    if (!notificationRepository.findById(id).isPresent())
      throw new NotificationNotFoundException(id);
    notificationRepository.deleteById(id);
  }
}
