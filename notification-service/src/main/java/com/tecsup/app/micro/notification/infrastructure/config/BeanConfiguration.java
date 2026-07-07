package com.tecsup.app.micro.notification.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tecsup.app.micro.notification.application.usecase.CreateNotificationUseCase;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import com.tecsup.app.micro.notification.shared.infrastructure.event.KafkaEventPublisher;

@Configuration
public class BeanConfiguration {
  @Bean
  public CreateNotificationUseCase createNotificationUseCase(NotificationRepository repository,
      KafkaEventPublisher eventPublisher) {

    return new CreateNotificationUseCase(repository, eventPublisher);

  }
}
