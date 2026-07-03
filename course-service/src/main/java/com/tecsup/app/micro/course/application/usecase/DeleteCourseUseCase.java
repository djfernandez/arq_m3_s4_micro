package com.tecsup.app.micro.course.application.usecase;

import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCourseUseCase {

  private final CourseRepository courseRepository;

  public void execute(Long id) {
    log.debug("Executing DeleteCourseUseCase for id: {}", id);

    if (!courseRepository.findById(id).isPresent()) {
      throw new CourseNotFoundException(id);
    }

    courseRepository.deleteById(id);
  }
}
