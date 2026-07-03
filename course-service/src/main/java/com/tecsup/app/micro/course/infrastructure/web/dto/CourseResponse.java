package com.tecsup.app.micro.course.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

  private Long id;
  private String title;
  private String description;
  private String instructor;
  private BigDecimal price;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
