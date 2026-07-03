package com.tecsup.app.micro.enrollment.application.usecase;

import com.tecsup.app.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateEnrollmentUseCase {

  private final EnrollmentRepository enrollmentRepository;

  public Enrollment execute(Long id, Enrollment enrollment) {
    log.debug("Executing UpdateEnrollmentUseCase for id: {}", id);

    Enrollment existing = enrollmentRepository.findById(id)
        .orElseThrow(() -> new EnrollmentNotFoundException(id));

    if (enrollment.getStatus() != null) {
      existing.setStatus(enrollment.getStatus());
    }

    return enrollmentRepository.save(existing);
  }
}
