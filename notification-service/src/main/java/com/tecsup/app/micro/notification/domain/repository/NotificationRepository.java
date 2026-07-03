package com.tecsup.app.micro.notification.domain.repository;

import com.tecsup.app.micro.notification.domain.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

  List<Notification> findAll();

  Optional<Notification> findById(Long id);

  List<Notification> findByUserId(Long userId);

  List<Notification> findByUserIdAndRead(Long userId, Boolean read);

  Notification save(Notification notification);

  void deleteById(Long id);
}
