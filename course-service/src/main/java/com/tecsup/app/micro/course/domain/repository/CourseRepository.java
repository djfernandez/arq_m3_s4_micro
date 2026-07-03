package com.tecsup.app.micro.course.domain.repository;

import com.tecsup.app.micro.course.domain.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * Puerto del Repositorio de Curso (Interface)
 * Define el contrato para la persistencia sin depender de la implementación
 */
public interface CourseRepository {

  List<Course> findAll();

  Optional<Course> findById(Long id);

  Course save(Course course);

  void deleteById(Long id);

  boolean existsByTitle(String title);
}
