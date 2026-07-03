package com.tecsup.app.micro.course.infrastructure.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {

  @NotBlank(message = "Title is required")
  @Size(max = 150, message = "Title must not exceed 150 characters")
  private String title;

  @Size(max = 500, message = "Description must not exceed 500 characters")
  private String description;

  @NotBlank(message = "Instructor is required")
  @Size(max = 100, message = "Instructor must not exceed 100 characters")
  private String instructor;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.0", message = "Price must be zero or positive")
  private BigDecimal price;
}
