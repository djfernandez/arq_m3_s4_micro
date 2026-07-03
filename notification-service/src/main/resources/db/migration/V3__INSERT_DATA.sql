-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO notifications (user_id, type, subject, message, read) VALUES
(1, 'EMAIL', 'Bienvenido', 'Te has registrado exitosamente en la plataforma.', TRUE),
(1, 'EMAIL', 'Matrícula confirmada', 'Tu matrícula en Arquitectura de Software ha sido confirmada.', FALSE),
(2, 'SMS',   NULL, 'Tu pago de S/. 249.99 fue procesado correctamente.', TRUE),
(3, 'PUSH',  'Nuevo curso disponible', 'Se ha publicado el curso Docker y Kubernetes.', FALSE),
(4, 'EMAIL', 'Recordatorio', 'Tu curso Microservicios con Spring Boot inicia mañana.', FALSE);
