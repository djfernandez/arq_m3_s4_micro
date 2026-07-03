package com.tecsup.app.micro.enrollment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", indexes = {
    @Index(name = "idx_enrollments_user_id", columnList = "user_id"),
    @Index(name = "idx_enrollments_course_id", columnList = "course_id"),
    @Index(name = "idx_enrollments_status", columnList = "status")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "course_id", nullable = false)
  private Long courseId;

  @Column(nullable = false, length = 20)
  private String status;

  @Column(name = "enrolled_at", nullable = false, updatable = false)
  private LocalDateTime enrolledAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    enrolledAt = now;
    updatedAt = now;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
