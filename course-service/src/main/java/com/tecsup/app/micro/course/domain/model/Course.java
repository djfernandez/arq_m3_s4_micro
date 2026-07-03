package com.tecsup.app.micro.course.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
  private String instructor;
  private BigDecimal price;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public boolean isValid() {
    return title != null && !title.trim().isEmpty()
        && instructor != null && !instructor.trim().isEmpty()
        && price != null && price.compareTo(BigDecimal.ZERO) >= 0;
  }
}
