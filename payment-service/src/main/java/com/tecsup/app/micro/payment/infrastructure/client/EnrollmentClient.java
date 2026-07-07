package com.tecsup.app.micro.payment.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tecsup.app.micro.payment.infrastructure.client.dto.EnrollmentDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnrollmentClient {

  private final RestTemplate restTemplate;

  @Value("${enrollment.service.url}")
  private String enrollmentServiceUrl;

  public EnrollmentDTO getEnrollmentById(Long enrollmentId, EnrollmentDTO enrollmentDTO) {
    log.info("Calling Enrollment Service to get enrollment with id: {}", enrollmentId);

    String url = this.enrollmentServiceUrl + "/api/enrollments/" + enrollmentId;

    try {
      restTemplate.put(url, enrollmentDTO, enrollmentId);
      log.info("Enrollment updated successfully: {}", enrollmentDTO);
      return enrollmentDTO;
    } catch (Exception e) {
      log.error("Error calling Enrollment Service: {}", e.getMessage());
      throw new RuntimeException("Error calling Enrollment Service: " + e.getMessage());
    }
  }
}
