package com.tecsup.app.micro.notification.domain.event;

import com.tecsup.app.micro.notification.shared.domain.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor(force = true)
public class CoursePublishedEvent extends DomainEvent {

  private final String id;
  private final String userId;
  private final String title;

  @Override
  public String getKey() { // SOBREESCRIBIR EL METODO
    return this.id;
  }
}
