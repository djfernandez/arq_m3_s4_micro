-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO payments (enrollment_id, amount, status) VALUES
(1, 299.99, 'COMPLETED'),
(2, 249.99, 'COMPLETED'),
(3, 299.99, 'REFUNDED'),
(4, 199.99, 'PENDING'),
(5, 249.99, 'FAILED');
