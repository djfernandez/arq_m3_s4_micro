package com.tecsup.app.micro.enrollment.infrastructure.persistence.repository;

import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

  private final JpaEnrollmentRepository jpaEnrollmentRepository;

  @Override
  public List<Enrollment> findAll() {
    return jpaEnrollmentRepository.findAll().stream()
        .map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Optional<Enrollment> findById(Long id) {
    return jpaEnrollmentRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<Enrollment> findByUserId(Long userId) {
    return jpaEnrollmentRepository.findByUserId(userId).stream()
        .map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public List<Enrollment> findByCourseId(Long courseId) {
    return jpaEnrollmentRepository.findByCourseId(courseId).stream()
        .map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Enrollment save(Enrollment enrollment) {
    return toDomain(jpaEnrollmentRepository.save(toEntity(enrollment)));
  }

  @Override
  public void deleteById(Long id) {
    jpaEnrollmentRepository.deleteById(id);
  }

  @Override
  public boolean existsByUserIdAndCourseId(Long userId, Long courseId) {
    return jpaEnrollmentRepository.existsByUserIdAndCourseId(userId, courseId);
  }

  private Enrollment toDomain(EnrollmentEntity entity) {
    return Enrollment.builder()
        .id(entity.getId())
        .userId(entity.getUserId())
        .courseId(entity.getCourseId())
        .status(entity.getStatus())
        .enrolledAt(entity.getEnrolledAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  private EnrollmentEntity toEntity(Enrollment enrollment) {
    return EnrollmentEntity.builder()
        .id(enrollment.getId())
        .userId(enrollment.getUserId())
        .courseId(enrollment.getCourseId())
        .status(enrollment.getStatus())
        .build();
  }
}
