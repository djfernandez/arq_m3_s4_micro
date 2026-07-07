package com.tecsup.app.micro.enrollment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.app.micro.enrollment.application.usecase.CreateEnrollmentUseCase;
import com.tecsup.app.micro.enrollment.application.usecase.DeleteEnrollmentUseCase;
import com.tecsup.app.micro.enrollment.application.usecase.GetAllEnrollmentsUseCase;
import com.tecsup.app.micro.enrollment.application.usecase.GetEnrollmentByIdUseCase;
import com.tecsup.app.micro.enrollment.application.usecase.GetEnrollmentByUserIdUseCase;
import com.tecsup.app.micro.enrollment.application.usecase.UpdateEnrollmentUseCase;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentApplicationService {

  private final GetAllEnrollmentsUseCase getAllEnrollmentsUseCase;
  private final GetEnrollmentByIdUseCase getEnrollmentByIdUseCase;
  private final CreateEnrollmentUseCase createEnrollmentUseCase;
  private final UpdateEnrollmentUseCase updateEnrollmentUseCase;
  private final DeleteEnrollmentUseCase deleteEnrollmentUseCase;
  private final GetEnrollmentByUserIdUseCase getEnrollmentByUserIdUseCase;

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

  @Transactional(readOnly = true)
  public List<Enrollment> getEnrollmentByUserId(Long userId) {
    return getEnrollmentByUserIdUseCase.execute(userId);
  }
}
