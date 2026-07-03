package com.tecsup.app.micro.enrollment.application.usecase;

import com.tecsup.app.micro.enrollment.domain.exception.DuplicateEnrollmentException;
import com.tecsup.app.micro.enrollment.domain.exception.InvalidEnrollmentDataException;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateEnrollmentUseCase {

  private final EnrollmentRepository enrollmentRepository;

  public Enrollment execute(Enrollment enrollment) {
    log.debug("Executing CreateEnrollmentUseCase for userId: {} courseId: {}",
        enrollment.getUserId(), enrollment.getCourseId());

    if (!enrollment.isValid()) {
      throw new InvalidEnrollmentDataException("Invalid enrollment data. UserId and courseId are required.");
    }

    if (enrollmentRepository.existsByUserIdAndCourseId(enrollment.getUserId(), enrollment.getCourseId())) {
      throw new DuplicateEnrollmentException(enrollment.getUserId(), enrollment.getCourseId());
    }

    enrollment.setStatus("ACTIVE");
    return enrollmentRepository.save(enrollment);
  }
}
