package com.tecsup.app.micro.notification.application.service;

import com.tecsup.app.micro.notification.application.usecase.*;
import com.tecsup.app.micro.notification.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationApplicationService {

  private final GetAllNotificationsUseCase getAllNotificationsUseCase;
  private final GetNotificationByIdUseCase getNotificationByIdUseCase;
  private final CreateNotificationUseCase createNotificationUseCase;
  private final MarkNotificationReadUseCase markNotificationReadUseCase;
  private final DeleteNotificationUseCase deleteNotificationUseCase;

  @Transactional(readOnly = true)
  public List<Notification> getAllNotifications() {
    return getAllNotificationsUseCase.execute();
  }

  @Transactional(readOnly = true)
  public Notification getNotificationById(Long id) {
    return getNotificationByIdUseCase.execute(id);
  }

  @Transactional
  public Notification createNotification(Notification notification) {
    return createNotificationUseCase.execute(notification);
  }

  @Transactional
  public Notification markAsRead(Long id) {
    return markNotificationReadUseCase.execute(id);
  }

  @Transactional
  public void deleteNotification(Long id) {
    deleteNotificationUseCase.execute(id);
  }
}
