package com.tecsup.app.micro.notification.infrastructure.web.controller;

import com.tecsup.app.micro.notification.application.service.NotificationApplicationService;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CreateNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;
import com.tecsup.app.micro.notification.infrastructure.web.mapper.NotificationDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping
  public ResponseEntity<NotificationResponse> createNotification(
      @Valid @RequestBody CreateNotificationRequest request) {
    log.info("REST request to create notification for userId: {}", request.getUserId());
    Notification notification = notificationDtoMapper.toDomain(request);
    Notification created = notificationApplicationService.createNotification(notification);
    return ResponseEntity.status(HttpStatus.CREATED).body(notificationDtoMapper.toResponse(created));
  }

  @PatchMapping("/{id}/read")
  public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long id) {
    log.info("REST request to mark notification {} as read", id);
    return ResponseEntity.ok(notificationDtoMapper.toResponse(notificationApplicationService.markAsRead(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
    log.info("REST request to delete notification with id: {}", id);
    notificationApplicationService.deleteNotification(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Notification Service running with Clean Architecture!");
  }
}
