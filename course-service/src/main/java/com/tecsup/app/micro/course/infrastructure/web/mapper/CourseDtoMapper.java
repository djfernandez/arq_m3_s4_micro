package com.tecsup.app.micro.course.infrastructure.web.mapper;

import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.web.dto.CreateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.UpdateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper entre DTOs de presentación y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface CourseDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "published", ignore = true)
  Course toDomain(CreateCourseRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Course toDomain(UpdateCourseRequest request);

  CourseResponse toResponse(Course course);

  List<CourseResponse> toResponseList(List<Course> courses);
}
