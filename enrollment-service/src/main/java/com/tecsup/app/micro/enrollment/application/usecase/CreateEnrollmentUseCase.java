package com.tecsup.app.micro.enrollment.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.enrollment.domain.exception.DuplicateEnrollmentException;
import com.tecsup.app.micro.enrollment.domain.exception.InvalidEnrollmentDataException;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.client.CourseClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.NotificationClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.UserClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.CourseDTO;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.NotificationRequest;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateEnrollmentUseCase {

  private final EnrollmentRepository enrollmentRepository;
  private final NotificationClient notificationClient;
  private final UserClient userClient;
  private final CourseClient courseClient;

  public Enrollment execute(Enrollment enrollment) {
    log.debug("Executing CreateEnrollmentUseCase for userId: {} courseId: {}",
        enrollment.getUserId(), enrollment.getCourseId());

    if (!enrollment.isValid()) {
      throw new InvalidEnrollmentDataException("Invalid enrollment data. UserId and courseId are required.");
    }

    if (enrollmentRepository.existsByUserIdAndCourseId(enrollment.getUserId(), enrollment.getCourseId())) {
      throw new DuplicateEnrollmentException(enrollment.getUserId(), enrollment.getCourseId());
    }

    UserDTO user = userClient.getUserById(enrollment.getUserId());
    log.info("Creating enrollment for user: {}", user.getFull_name());

    CourseDTO course = courseClient.getCourseById(enrollment.getCourseId());
    log.info("Creating enrollment for course: {}", course.getTitle());

    if (!user.getStatus().equalsIgnoreCase("ACTIVE")) {
      throw new InvalidEnrollmentDataException("Cannot enroll an inactive user.");
    }

    if (!course.getPublished()) {
      throw new InvalidEnrollmentDataException("Cannot enroll in an unpublished course.");
    }

    Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
    log.info("Enrollment created successfully with id: {}", savedEnrollment.getId());

    NotificationRequest event = new NotificationRequest(
        savedEnrollment.getCourseId().toString(),
        savedEnrollment.getUserId().toString(),
        course.getTitle());

    notificationClient.NotificationPublished(event);

    return savedEnrollment;
  }
}
