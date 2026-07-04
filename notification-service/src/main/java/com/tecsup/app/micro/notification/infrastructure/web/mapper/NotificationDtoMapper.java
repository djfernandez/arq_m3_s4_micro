package com.tecsup.app.micro.notification.infrastructure.web.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CreateNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;

@Component
public class NotificationDtoMapper {

  public Notification toDomain(CreateNotificationRequest request) {
    if (request == null) {
      return null;
    }
    return Notification.builder()
        .userId(request.getUserId())
        .message(request.getMessage())
        .sent(Boolean.FALSE)
        .createdAt(LocalDateTime.now())
        .build();
  }

  public NotificationResponse toResponse(Notification notification) {
    if (notification == null) {
      return null;
    }
    return NotificationResponse.builder()
        .id(notification.getId())
        .userId(notification.getUserId())
        .message(notification.getMessage())
        .sent(notification.getSent())
        .createdAt(notification.getCreatedAt())
        .build();
  }

  public List<NotificationResponse> toResponseList(List<Notification> notifications) {
    if (notifications == null) {
      return List.of();
    }
    return notifications.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }
}
