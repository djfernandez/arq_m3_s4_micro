package com.tecsup.app.micro.enrollment.application.usecase;

import com.tecsup.app.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteEnrollmentUseCase {

  private final EnrollmentRepository enrollmentRepository;

  public void execute(Long id) {
    log.debug("Executing DeleteEnrollmentUseCase for id: {}", id);
    if (!enrollmentRepository.findById(id).isPresent()) {
      throw new EnrollmentNotFoundException(id);
    }
    enrollmentRepository.deleteById(id);
  }
}
