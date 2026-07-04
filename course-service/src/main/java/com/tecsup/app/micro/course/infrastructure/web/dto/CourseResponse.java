package com.tecsup.app.micro.course.infrastructure.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

  private Long id;
  private String title;
  private String description;
  private Boolean publisher;
  private LocalDateTime createdAt;

}
