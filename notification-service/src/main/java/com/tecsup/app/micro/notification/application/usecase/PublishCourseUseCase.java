package com.tecsup.app.micro.notification.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.CoursePublishedEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishCourseUseCase {

  private final KafkaEventPublisher eventPublisher;

  public void execute(String courseId, String userId, String title) {

    CoursePublishedEvent event = new CoursePublishedEvent(courseId, userId, title);
    // Publish event
    eventPublisher.publishCourseEvent(event);
  }
}
