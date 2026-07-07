package com.tecsup.app.micro.notification.infrastructure.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CoursePublishNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CreateNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.EnrollmentPublishNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;

@Mapper(componentModel = "spring")
public interface NotificationDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sent", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Notification toDomain(CreateNotificationRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sent", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Notification toDomain(CoursePublishNotificationRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sent", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Notification toDomain(EnrollmentPublishNotificationRequest request);

  NotificationResponse toResponse(Notification notification);

  List<NotificationResponse> toResponseList(List<Notification> notifications);
}
