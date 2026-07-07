package com.tecsup.app.micro.course.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.course.domain.event.CoursePublishedEvent;
import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishCourseUseCase {

  private final CourseRepository courseRepository;
  private final KafkaEventPublisher eventPublisher;

  public Course execute(Long id) {
    log.debug("Executing PublishCourseUseCase for id: {}", id);

    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));

    course.setPublished(true);
    Course saved = courseRepository.save(course);

    log.info("Course updated: {}", saved.getId());

    CoursePublishedEvent event = new CoursePublishedEvent(
        saved.getId().toString(),
        saved.getTitle());

    eventPublisher.publish(event);

    return saved;
  }
}
