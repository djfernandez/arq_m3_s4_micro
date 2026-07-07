package com.tecsup.app.micro.notification.application.eventhandler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.notification.domain.event.CoursePublishedEvent;
import com.tecsup.app.micro.notification.shared.domain.event.DomainEvent;
import com.tecsup.app.micro.notification.shared.infrastructure.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CourseEventHandler {

  @KafkaListener(topics = KafkaConfig.COURSE_EVENT_TOPIC, // Topico que va a escuchando
      groupId = "course-notifications-group" // Grupo de consumidores
  )
  public void handleCourseEvents(DomainEvent event) {
    if (event instanceof CoursePublishedEvent) {
      this.handleCoursePublished((CoursePublishedEvent) event);
    } else {
      throw new RuntimeException("Invalid event type " + event.getClass());
    }
  }

  private void handleCoursePublished(CoursePublishedEvent event) {
    log.info("[Kafka] Course published event received: {}", event);

  }

}