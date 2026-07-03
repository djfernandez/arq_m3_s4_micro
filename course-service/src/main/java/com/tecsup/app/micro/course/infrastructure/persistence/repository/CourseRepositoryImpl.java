package com.tecsup.app.micro.course.infrastructure.persistence.repository;

import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.infrastructure.persistence.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de Curso (Adaptador)
 * Conecta el dominio con la infraestructura de persistencia
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class CourseRepositoryImpl implements CourseRepository {

  private final JpaCourseRepository jpaCourseRepository;

  @Override
  public List<Course> findAll() {
    return jpaCourseRepository.findAll()
        .stream()
        .map(this::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Course> findById(Long id) {
    return jpaCourseRepository.findById(id).map(this::toDomain);
  }

  @Override
  public Course save(Course course) {
    CourseEntity entity = toEntity(course);
    return toDomain(jpaCourseRepository.save(entity));
  }

  @Override
  public void deleteById(Long id) {
    jpaCourseRepository.deleteById(id);
  }

  @Override
  public boolean existsByTitle(String title) {
    return jpaCourseRepository.existsByTitle(title);
  }

  private Course toDomain(CourseEntity entity) {
    return Course.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .instructor(entity.getInstructor())
        .price(entity.getPrice())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  private CourseEntity toEntity(Course course) {
    return CourseEntity.builder()
        .id(course.getId())
        .title(course.getTitle())
        .description(course.getDescription())
        .instructor(course.getInstructor())
        .price(course.getPrice())
        .build();
  }
}
