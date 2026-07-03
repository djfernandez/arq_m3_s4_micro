package com.tecsup.app.micro.course.infrastructure.web.mapper;

import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.infrastructure.web.dto.CreateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.UpdateCourseRequest;
import com.tecsup.app.micro.course.infrastructure.web.dto.CourseResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper entre DTOs de presentación y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface CourseDtoMapper {

  Course toDomain(CreateCourseRequest request);

  Course toDomain(UpdateCourseRequest request);

  CourseResponse toResponse(Course course);

  List<CourseResponse> toResponseList(List<Course> courses);
}
