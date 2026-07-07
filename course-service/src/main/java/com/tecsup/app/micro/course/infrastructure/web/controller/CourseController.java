package com.tecsup.app.micro.course.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.app.micro.course.application.service.CourseApplicationService;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.web.dto.CourseResponse;
import com.tecsup.app.micro.course.infrastructure.web.dto.CreateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.PublishCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.UpdateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.mapper.CourseDtoMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST de Cursos
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

  private final CourseApplicationService courseApplicationService;
  private final CourseDtoMapper courseDtoMapper;

  @GetMapping
  public ResponseEntity<List<CourseResponse>> getAllCourses() {
    log.info("REST request to get all courses");
    List<Course> courses = courseApplicationService.getAllCourses();
    return ResponseEntity.ok(courseDtoMapper.toResponseList(courses));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
    log.info("REST request to get course by id: {}", id);
    Course course = courseApplicationService.getCourseById(id);
    return ResponseEntity.ok(courseDtoMapper.toResponse(course));
  }

  @PostMapping
  public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CreateCourseRequest request) {
    log.info("REST request to create course: {}", request.getTitle());
    Course course = courseDtoMapper.toDomain(request);
    Course created = courseApplicationService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(courseDtoMapper.toResponse(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseResponse> updateCourse(
      @PathVariable Long id,
      @Valid @RequestBody UpdateCourseRequest request) {
    log.info("REST request to update course with id: {}", id);
    Course course = courseDtoMapper.toDomain(request);
    Course updated = courseApplicationService.updateCourse(id, course);
    return ResponseEntity.ok(courseDtoMapper.toResponse(updated));
  }

  @PutMapping("/publish")
  public ResponseEntity<CourseResponse> publishCourse(@Valid @RequestBody PublishCourseRequest request) {
    log.info("REST request to publish course with id: {}", request.getId());
    Course updated = courseApplicationService.publishCourse(request.getId(), request.getUserId());
    return ResponseEntity.ok(courseDtoMapper.toResponse(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    log.info("REST request to delete course with id: {}", id);
    courseApplicationService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Course Service running with Clean Architecture!");
  }
}
