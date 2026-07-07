package com.tecsup.app.micro.course.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.infrastructure.client.NotificationClient;
import com.tecsup.app.micro.course.infrastructure.client.dto.NotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishCourseUseCase {

  private final CourseRepository courseRepository;
  private final NotificationClient notificationClient;

  public Course execute(Long id, Long userId) {
    log.debug("Executing PublishCourseUseCase for id: {}", id);

    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));

    course.setPublished(true);
    Course saved = courseRepository.save(course);

    log.info("Course updated: {}", saved.getId());

    NotificationRequest event = new NotificationRequest(
        id.toString(),
        userId.toString(),
        saved.getTitle());

    notificationClient.NotificationPublished(event);

    // Aquí podrías publicar el evento si tienes un EventBus o similar
    // eventPublisher.publish(event);

    return saved;
  }
}
