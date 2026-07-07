package com.tecsup.app.micro.enrollment.domain.event;

import com.tecsup.app.micro.enrollment.shared.domain.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor(force = true)
public class EnrollmentUpdatedEvent extends DomainEvent {
  private final String courseId;
  private final String userId;
  private final String status;

  @Override
  public String getKey() { // SOBREESCRIBIR EL METODO
    return this.courseId;
  }
}
