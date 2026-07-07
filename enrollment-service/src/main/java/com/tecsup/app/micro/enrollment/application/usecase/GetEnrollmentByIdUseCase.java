package com.tecsup.app.micro.enrollment.application.usecase;

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
public class GetEnrollmentByIdUseCase {

  private final EnrollmentRepository enrollmentRepository;
  private final UserClient userClient;

  public Enrollment execute(Long id) {
    log.debug("Executing GetEnrollmentByIdUseCase for id: {}", id);

    Enrollment enrollment = enrollmentRepository.findById(id)
        .orElseThrow(() -> new EnrollmentNotFoundException(id));

    // --------------------------------------------------------
    // Llama al microservicio user-service
    // --------------------------------------------------------
    // Validar que el usuario existe en userdb
    UserDTO user = userClient.getUserById(enrollment.getUserId());
    log.info("Fetching enrollment for user from userdb: {}", user.getFull_name());

    // enrollment.setUserName(user.getFull_name());

    log.info("Enrollment: {}", enrollment);

    return enrollment;
  }
}
