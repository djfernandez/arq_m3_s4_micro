package com.tecsup.app.micro.course.application.usecase;

import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.exception.InvalidCourseDataException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCourseUseCase {

  private final CourseRepository courseRepository;

  public Course execute(Long id, Course course) {
    log.debug("Executing UpdateCourseUseCase for id: {}", id);

    Course existing = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));

    if (!course.isValid()) {
      throw new InvalidCourseDataException("Invalid course data. Title, instructor and price are required.");
    }

    existing.setTitle(course.getTitle());
    existing.setDescription(course.getDescription());
    existing.setInstructor(course.getInstructor());
    existing.setPrice(course.getPrice());

    return courseRepository.save(existing);
  }
}
