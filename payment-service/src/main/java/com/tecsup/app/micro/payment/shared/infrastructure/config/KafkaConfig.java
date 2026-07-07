package com.tecsup.app.micro.payment.shared.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {
  // Set TOPICS
  public static final String PAYMENT_EVENT_TOPIC = "payment.events";

  // Set QUEUES/PARTITIONS

  /**
   * Topic de eventos de pago
   *
   * @return
   */
  @Bean
  public NewTopic paymentEventTopic() {

    return new NewTopic(PAYMENT_EVENT_TOPIC, // topic
        3, // Nro. particiones
        (short) 1 // Nro. de replicas
    );
  }
}
