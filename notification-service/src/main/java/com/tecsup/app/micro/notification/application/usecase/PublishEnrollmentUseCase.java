package com.tecsup.app.micro.notification.application.usecase;

import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.EnrollmentPublishedEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishEnrollmentUseCase {
  private final KafkaEventPublisher eventPublisher;

  public void execute(String enrollmentId, String userId, String title) {

    EnrollmentPublishedEvent event = new EnrollmentPublishedEvent(enrollmentId, userId, title);
    // Publish event
    eventPublisher.publishEnrollmentEvent(event);
  }
}
