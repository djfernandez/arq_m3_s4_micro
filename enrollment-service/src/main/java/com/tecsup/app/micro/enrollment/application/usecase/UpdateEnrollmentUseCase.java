package com.tecsup.app.micro.enrollment.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.enrollment.domain.event.EnrollmentUpdatedEvent;
import com.tecsup.app.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.client.NotificationClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.NotificationDTO;
import com.tecsup.app.micro.enrollment.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateEnrollmentUseCase {

  private final EnrollmentRepository enrollmentRepository;
  private final KafkaEventPublisher eventPublisher;
  private final NotificationClient notificationClient;

  public Enrollment execute(Long id, Enrollment enrollment) {
    log.debug("Executing UpdateEnrollmentUseCase for id: {}", id);

    Enrollment existing = enrollmentRepository.findById(id)
        .orElseThrow(() -> new EnrollmentNotFoundException(id));

    if (enrollment.getStatus() != null) {
      existing.setStatus(enrollment.getStatus());
    }

    Enrollment updatedEnrollment = enrollmentRepository.save(existing);
    log.info("Enrollment updated successfully with id: {}", updatedEnrollment.getId());

    NotificationDTO notification = new NotificationDTO(
        updatedEnrollment.getUserId().toString(),
        enrollment.getStatus() != null ? "Your enrollment status has been updated to: " + enrollment.getStatus()
            : "Your enrollment has been updated.");

    EnrollmentUpdatedEvent event = new EnrollmentUpdatedEvent(
        updatedEnrollment.getCourseId().toString(),
        updatedEnrollment.getUserId().toString(),
        updatedEnrollment.getStatus());

    notificationClient.NotificationPublished(notification);

    eventPublisher.publish(event);

    return updatedEnrollment;
  }
}
