package com.tecsup.app.micro.notification.infrastructure.web.mapper;

import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.infrastructure.web.dto.CreateNotificationRequest;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationDtoMapper {

  Notification toDomain(CreateNotificationRequest request);

  NotificationResponse toResponse(Notification notification);

  List<NotificationResponse> toResponseList(List<Notification> notifications);
}
