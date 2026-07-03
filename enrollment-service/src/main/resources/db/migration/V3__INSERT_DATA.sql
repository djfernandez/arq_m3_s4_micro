-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO enrollments (user_id, course_id, status) VALUES
(1, 1, 'ACTIVE'),
(1, 2, 'ACTIVE'),
(2, 1, 'COMPLETED'),
(3, 3, 'ACTIVE'),
(4, 2, 'CANCELLED');
