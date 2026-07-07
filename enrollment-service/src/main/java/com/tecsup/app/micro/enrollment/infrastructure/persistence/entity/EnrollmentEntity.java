package com.tecsup.app.micro.enrollment.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    createdAt = now;
    status = "PENDING_PAYMENT"; // Set default status to PENDING_PAYMENT
  }
}
