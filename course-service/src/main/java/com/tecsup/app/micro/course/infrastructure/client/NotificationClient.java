package com.tecsup.app.micro.course.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tecsup.app.micro.course.infrastructure.client.dto.NotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationClient {
  private final RestTemplate restTemplate;

  @Value("${notification.service.url}")
  private String notificationServiceUrl;

  public String NotificationPublished(NotificationRequest event) {
    log.info("Calling Notification Service to get notification with id: {}", event.getUserId());

    String url = this.notificationServiceUrl + "/api/notifications/publish-course-event";

    try {
      String notification = restTemplate.postForEntity(url, event, String.class).getBody();
      log.info("Notification retrieved successfully from notification service: {}", notification);
      return notification != null ? notification : "";
    } catch (Exception e) {
      log.error("Error calling Notification Service: {}", e.getMessage());
      throw new RuntimeException("Error calling Notification Service: " + e.getMessage());
    }
  }
}
