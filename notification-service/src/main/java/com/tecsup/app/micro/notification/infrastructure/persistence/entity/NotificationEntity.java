package com.tecsup.app.micro.notification.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications", indexes = {
    @Index(name = "idx_notifications_user_id", columnList = "user_id"),
    @Index(name = "idx_notifications_read", columnList = "read"),
    @Index(name = "idx_notifications_type", columnList = "type")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(nullable = false, length = 20)
  private String type;

  @Column(length = 200)
  private String subject;

  @Column(nullable = false, length = 1000)
  private String message;

  @Column(nullable = false)
  private Boolean read;

  @Column(name = "sent_at", nullable = false, updatable = false)
  private LocalDateTime sentAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    sentAt = now;
    updatedAt = now;
    if (read == null)
      read = false;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
