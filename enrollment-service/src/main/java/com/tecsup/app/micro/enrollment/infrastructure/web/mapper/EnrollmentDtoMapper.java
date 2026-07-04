package com.tecsup.app.micro.enrollment.infrastructure.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.EnrollmentResponse;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.UpdateEnrollmentRequest;

@Mapper(componentModel = "spring")
public interface EnrollmentDtoMapper {

  Enrollment toDomain(CreateEnrollmentRequest request);

  Enrollment toDomain(UpdateEnrollmentRequest request);

  EnrollmentResponse toResponse(Enrollment enrollment);

  List<EnrollmentResponse> toResponseList(List<Enrollment> enrollments);
}
