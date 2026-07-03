-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO courses (title, description, instructor, price) VALUES
('Arquitectura de Software', 'Principios y patrones de arquitectura moderna', 'Dr. Carlos Mendoza', 299.99),
('Microservicios con Spring Boot', 'Diseño e implementación de microservicios', 'Ing. Ana Torres', 249.99),
('Docker y Kubernetes', 'Contenedores y orquestación en producción', 'Ing. Luis Ramos', 199.99),
('Bases de Datos Avanzadas', 'PostgreSQL, optimización y modelado', 'Dr. María García', 179.99),
('DevOps y CI/CD', 'Integración y despliegue continuo', 'Ing. Roberto Sánchez', 149.99);
