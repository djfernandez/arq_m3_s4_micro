package com.tecsup.app.micro.enrollment.application.usecase;

import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllEnrollmentsUseCase {

  private final EnrollmentRepository enrollmentRepository;

  public List<Enrollment> execute() {
    log.debug("Executing GetAllEnrollmentsUseCase");
    return enrollmentRepository.findAll();
  }
}
