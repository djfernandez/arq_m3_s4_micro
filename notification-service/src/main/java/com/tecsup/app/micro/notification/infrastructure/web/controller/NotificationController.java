package com.tecsup.app.micro.notification.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.app.micro.notification.application.service.NotificationApplicationService;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CoursePublishNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.EnrollmentPublishNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;
import com.tecsup.app.micro.notification.infrastructure.web.mapper.NotificationDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

  private final NotificationApplicationService notificationApplicationService;
  private final NotificationDtoMapper notificationDtoMapper;

  @GetMapping
  public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
    log.info("REST request to get all notifications");
    return ResponseEntity
        .ok(notificationDtoMapper.toResponseList(notificationApplicationService.getAllNotifications()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
    log.info("REST request to get notification by id: {}", id);
    return ResponseEntity.ok(notificationDtoMapper.toResponse(notificationApplicationService.getNotificationById(id)));
  }

  // @PostMapping
  // public ResponseEntity<NotificationResponse> createNotification(
  // @Valid @RequestBody CreateNotificationRequest request) {
  // log.info("REST request to create notification for userId: {}",
  // request.getUserId());
  // Notification notification = notificationDtoMapper.toDomain(request);
  // Notification created =
  // notificationApplicationService.createNotification(notification);
  // return
  // ResponseEntity.status(HttpStatus.CREATED).body(notificationDtoMapper.toResponse(created));
  // }

  // @PatchMapping("/{id}/read")
  // public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long id)
  // {
  // log.info("REST request to mark notification {} as read", id);
  // return
  // ResponseEntity.ok(notificationDtoMapper.toResponse(notificationApplicationService.markAsRead(id)));
  // }

  // @DeleteMapping("/{id}")
  // public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
  // log.info("REST request to delete notification with id: {}", id);
  // notificationApplicationService.deleteNotification(id);
  // return ResponseEntity.noContent().build();
  // }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Notification Service running with Clean Architecture!");
  }

  /**
   * Endpoint para publicar un evento de notificación (para pruebas)
   * 
   * @return
   */

  @PostMapping("/publish-course-event")
  public ResponseEntity<String> publishNotificationCourseEvent(@RequestBody CoursePublishNotificationRequest request) {
    log.info("REST request to publish notification course event for userId: {}", request.getUserId());

    Notification notification = notificationDtoMapper.toDomain(request);
    Notification created = notificationApplicationService.createNotification(notification);

    notificationApplicationService.publishCourse(request.getId().toString(), request.getUserId().toString(),
        request.getMessage());

    return ResponseEntity.ok("Notification course event published successfully!, {}" + created.getId());
  }

  @PostMapping("/publish-enrollment-event")
  public ResponseEntity<String> publishNotificationEnrollmentEvent(
      @RequestBody EnrollmentPublishNotificationRequest request) {
    log.info("REST request to publish notification enrollment event for userId: {}", request.getUserId());

    Notification notification = notificationDtoMapper.toDomain(request);
    Notification created = notificationApplicationService.createNotification(notification);

    notificationApplicationService.publishEnrollment(request.getId().toString(),
        request.getUserId().toString(),
        request.getMessage());

    return ResponseEntity.ok("Notification enrollment event published successfully!, {}" + created.getId());
  }

}
