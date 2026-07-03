-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO payments (enrollment_id, amount, status, method) VALUES
(1, 299.99, 'COMPLETED', 'CREDIT_CARD'),
(2, 249.99, 'COMPLETED', 'DEBIT_CARD'),
(3, 299.99, 'REFUNDED',  'CREDIT_CARD'),
(4, 199.99, 'PENDING',   'BANK_TRANSFER'),
(5, 249.99, 'FAILED',    'CREDIT_CARD');
