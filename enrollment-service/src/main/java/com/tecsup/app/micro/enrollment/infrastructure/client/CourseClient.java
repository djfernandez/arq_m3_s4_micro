package com.tecsup.app.micro.enrollment.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tecsup.app.micro.enrollment.infrastructure.client.dto.CourseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseClient {

  public final RestTemplate restTemplate;

  @Value("${course.service.url}")
  private String courseServiceUrl;

  public CourseDTO getCourseById(Long courseId) {
    log.info("Calling Course Service to get course with id: {}", courseId);

    String url = this.courseServiceUrl + "/api/courses/" + courseId;

    try {
      CourseDTO course = restTemplate.getForObject(url, CourseDTO.class);
      log.info("Course retrieved successfully: {}", course);
      return course;
    } catch (Exception e) {
      log.error("Error calling Course Service: {}", e.getMessage());
      throw new RuntimeException("Error calling Course Service: " + e.getMessage());
    }
  }

}
