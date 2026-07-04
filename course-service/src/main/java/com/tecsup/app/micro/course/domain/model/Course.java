package com.tecsup.app.micro.course.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Course Domain Model (Core Business Entity)
 * Entidad de dominio pura, sin dependencias de frameworks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  private Long id;
  private String title;
  private String description;
  private Boolean published;
  private LocalDateTime createdAt;

  public boolean isValid() {
    return title != null && !title.trim().isEmpty()
        && published != null;
  }
}
