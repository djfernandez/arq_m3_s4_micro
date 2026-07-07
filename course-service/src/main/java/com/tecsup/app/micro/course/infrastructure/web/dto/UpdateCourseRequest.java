package com.tecsup.app.micro.course.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @NotNull(message = "Published status is required")
  private Boolean published;

}
