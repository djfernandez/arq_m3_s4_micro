package com.tecsup.app.micro.notification.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import com.tecsup.app.micro.notification.infrastructure.persistence.entity.NotificationEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationRepositoryImpl implements NotificationRepository {

  private final JpaNotificationRepository jpaNotificationRepository;

  @Override
  public List<Notification> findAll() {
    return jpaNotificationRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Optional<Notification> findById(Long id) {
    if (id == null) {
      return Optional.empty();
    }
    return jpaNotificationRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<Notification> findByUserId(Long userId) {
    return jpaNotificationRepository.findByUserId(userId).stream().map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Notification save(Notification notification) {
    return toDomain(jpaNotificationRepository.save(toEntity(notification)));
  }

  @Override
  public void deleteById(Long id) {
    if (id != null) {
      jpaNotificationRepository.deleteById(id);
    }
  }

  private Notification toDomain(NotificationEntity entity) {
    return Notification.builder()
        .id(entity.getId())
        .userId(entity.getUserId())
        .message(entity.getMessage())
        .sent(entity.getSent())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  private NotificationEntity toEntity(Notification notification) {
    return NotificationEntity.builder()
        .id(notification.getId())
        .userId(notification.getUserId())
        .message(notification.getMessage())
        .sent(notification.getSent())
        .createdAt(notification.getCreatedAt())
        .build();
  }
}
