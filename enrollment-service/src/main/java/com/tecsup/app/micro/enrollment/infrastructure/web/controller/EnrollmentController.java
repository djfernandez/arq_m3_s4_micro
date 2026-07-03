package com.tecsup.app.micro.enrollment.infrastructure.web.controller;

import com.tecsup.app.micro.enrollment.application.service.EnrollmentApplicationService;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.EnrollmentResponse;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.UpdateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.mapper.EnrollmentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController {

  private final EnrollmentApplicationService enrollmentApplicationService;
  private final EnrollmentDtoMapper enrollmentDtoMapper;

  @GetMapping
  public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {
    log.info("REST request to get all enrollments");
    return ResponseEntity.ok(enrollmentDtoMapper.toResponseList(enrollmentApplicationService.getAllEnrollments()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long id) {
    log.info("REST request to get enrollment by id: {}", id);
    return ResponseEntity.ok(enrollmentDtoMapper.toResponse(enrollmentApplicationService.getEnrollmentById(id)));
  }

  @PostMapping
  public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody CreateEnrollmentRequest request) {
    log.info("REST request to create enrollment userId: {} courseId: {}", request.getUserId(), request.getCourseId());
    Enrollment enrollment = enrollmentDtoMapper.toDomain(request);
    Enrollment created = enrollmentApplicationService.createEnrollment(enrollment);
    return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentDtoMapper.toResponse(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EnrollmentResponse> updateEnrollment(
      @PathVariable Long id,
      @Valid @RequestBody UpdateEnrollmentRequest request) {
    log.info("REST request to update enrollment with id: {}", id);
    Enrollment enrollment = enrollmentDtoMapper.toDomain(request);
    return ResponseEntity
        .ok(enrollmentDtoMapper.toResponse(enrollmentApplicationService.updateEnrollment(id, enrollment)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
    log.info("REST request to delete enrollment with id: {}", id);
    enrollmentApplicationService.deleteEnrollment(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Enrollment Service running with Clean Architecture!");
  }
}
