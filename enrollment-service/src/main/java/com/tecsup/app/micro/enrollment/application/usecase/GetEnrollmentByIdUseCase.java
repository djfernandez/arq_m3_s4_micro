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
public class GetEnrollmentByIdUseCase {

  private final EnrollmentRepository enrollmentRepository;

  public Enrollment execute(Long id) {
    log.debug("Executing GetEnrollmentByIdUseCase for id: {}", id);
    return enrollmentRepository.findById(id)
        .orElseThrow(() -> new EnrollmentNotFoundException(id));
  }
}
