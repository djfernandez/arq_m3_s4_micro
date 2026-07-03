-- ============================================
-- Migration: V2__ADD_INDEXES.sql
-- ============================================

CREATE INDEX idx_payments_enrollment_id ON payments(enrollment_id);
CREATE INDEX idx_payments_status        ON payments(status);
CREATE INDEX idx_payments_paid_at       ON payments(paid_at DESC);
