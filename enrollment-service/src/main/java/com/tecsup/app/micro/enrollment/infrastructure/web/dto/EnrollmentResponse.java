package com.tecsup.app.micro.enrollment.infrastructure.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {

  private Long id;
  private Long userId;
  // private String userName;
  private Long courseId;
  // private String courseName;
  private String status;
  private LocalDateTime createdAt;
}
