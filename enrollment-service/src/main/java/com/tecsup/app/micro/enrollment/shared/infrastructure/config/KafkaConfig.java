package com.tecsup.app.micro.enrollment.shared.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {
  // Set TOPICS
  public static final String ENROLLMENT_EVENT_TOPIC = "enrollment.events";

  // Set QUEUES/PARTITIONS

  /**
   * Topic de eventos de inscripción
   *
   * @return
   */
  @Bean
  public NewTopic enrollmentEventTopic() {

    return new NewTopic(ENROLLMENT_EVENT_TOPIC, // topic
        3, // Nro. particiones
        (short) 1 // Nro. de replicas
    );
  }
}
