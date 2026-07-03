package com.tecsup.app.micro.enrollment.infrastructure.web.mapper;

import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.UpdateEnrollmentRequest;
import com.tecsup.app.micro.enrollment.infrastructure.web.dto.EnrollmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentDtoMapper {

  Enrollment toDomain(CreateEnrollmentRequest request);

  Enrollment toDomain(UpdateEnrollmentRequest request);

  EnrollmentResponse toResponse(Enrollment enrollment);

  List<EnrollmentResponse> toResponseList(List<Enrollment> enrollments);
}
