package com.tecsup.app.micro.enrollment.application.service;

import com.tecsup.app.micro.enrollment.application.usecase.*;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentApplicationService {

  private final GetAllEnrollmentsUseCase getAllEnrollmentsUseCase;
  private final GetEnrollmentByIdUseCase getEnrollmentByIdUseCase;
  private final CreateEnrollmentUseCase createEnrollmentUseCase;
  private final UpdateEnrollmentUseCase updateEnrollmentUseCase;
  private final DeleteEnrollmentUseCase deleteEnrollmentUseCase;

  @Transactional(readOnly = true)
  public List<Enrollment> getAllEnrollments() {
    return getAllEnrollmentsUseCase.execute();
  }

  @Transactional(readOnly = true)
  public Enrollment getEnrollmentById(Long id) {
    return getEnrollmentByIdUseCase.execute(id);
  }

  @Transactional
  public Enrollment createEnrollment(Enrollment enrollment) {
    return createEnrollmentUseCase.execute(enrollment);
  }

  @Transactional
  public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
    return updateEnrollmentUseCase.execute(id, enrollment);
  }

  @Transactional
  public void deleteEnrollment(Long id) {
    deleteEnrollmentUseCase.execute(id);
  }
}
