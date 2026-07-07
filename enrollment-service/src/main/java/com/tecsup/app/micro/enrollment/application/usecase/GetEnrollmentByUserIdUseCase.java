package com.tecsup.app.micro.enrollment.application.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.client.UserClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEnrollmentByUserIdUseCase {
  private final EnrollmentRepository enrollmentRepository;
  private final UserClient userClient;

  public List<Enrollment> execute(Long userId) {
    log.debug("Executing GetEnrollmentByUserIdUseCase for userId: {}", userId);

    List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);
    if (enrollments.isEmpty()) {
      throw new EnrollmentNotFoundException(userId);
    }

    // --------------------------------------------------------
    // Llama al microservicio user-service
    // --------------------------------------------------------
    // Validar que el usuario existe en userdb
    // UserDTO user = userClient.getUserById(userId);
    // log.info("Fetching enrollment for user from userdb: {}",
    // user.getFull_name());

    // enrollment.setUserName(user.getFull_name());

    log.info("Enrollment: {}", enrollments);

    return enrollments;
  }
}
