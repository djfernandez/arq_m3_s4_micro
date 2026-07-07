package com.tecsup.app.micro.notification.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.app.micro.notification.application.usecase.CreateNotificationUseCase;
import com.tecsup.app.micro.notification.application.usecase.GetAllNotificationsUseCase;
import com.tecsup.app.micro.notification.application.usecase.GetNotificationByIdUseCase;
import com.tecsup.app.micro.notification.application.usecase.PublishCourseUseCase;
import com.tecsup.app.micro.notification.application.usecase.PublishEnrollmentUseCase;
import com.tecsup.app.micro.notification.domain.model.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationApplicationService {

  private final GetAllNotificationsUseCase getAllNotificationsUseCase;
  private final GetNotificationByIdUseCase getNotificationByIdUseCase;
  private final CreateNotificationUseCase createNotificationUseCase;
  // private final MarkNotificationReadUseCase markNotificationReadUseCase;
  // private final DeleteNotificationUseCase deleteNotificationUseCase;
  private final PublishCourseUseCase publishCourseUseCase;
  private final PublishEnrollmentUseCase publishEnrollmentUseCase;

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

  // @Transactional
  // public Notification markAsRead(Long id) {
  // return markNotificationReadUseCase.execute(id);
  // }

  // @Transactional
  // public void deleteNotification(Long id) {
  // deleteNotificationUseCase.execute(id);
  // }

  @Transactional
  public void publishCourse(String id, String userId, String title) {
    publishCourseUseCase.execute(id, userId, title);
  }

  @Transactional
  public void publishEnrollment(String id, String userId, String title) {
    publishEnrollmentUseCase.execute(id, userId, title);
  }
}
