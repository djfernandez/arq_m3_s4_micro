package com.tecsup.app.micro.course.application.usecase;

import com.tecsup.app.micro.course.domain.exception.DuplicateCourseException;
import com.tecsup.app.micro.course.domain.exception.InvalidCourseDataException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCourseUseCase {

  private final CourseRepository courseRepository;

  public Course execute(Course course) {
    log.debug("Executing CreateCourseUseCase for title: {}", course.getTitle());

    if (!course.isValid()) {
      throw new InvalidCourseDataException("Invalid course data. Title, instructor and price are required.");
    }

    if (courseRepository.existsByTitle(course.getTitle())) {
      throw new DuplicateCourseException(course.getTitle());
    }

    return courseRepository.save(course);
  }
}
