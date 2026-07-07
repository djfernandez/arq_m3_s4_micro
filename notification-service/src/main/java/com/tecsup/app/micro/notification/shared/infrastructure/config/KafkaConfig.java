package com.tecsup.app.micro.notification.shared.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

  // Set TOPICS
  public static final String NOTIFICATION_EVENT_TOPIC = "notification.events";

  // Set QUEUES/PARTITIONS

  /**
   * Topic de eventos de notificación
   * 
   * @return
   */
  @Bean
  public NewTopic notificationEventTopic() {

    return new NewTopic(NOTIFICATION_EVENT_TOPIC, // topic
        3, // Nro. particiones
        (short) 1 // Nro. de replicas
    );
  }
}
