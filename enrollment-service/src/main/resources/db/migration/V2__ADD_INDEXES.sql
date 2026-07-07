-- ============================================
-- Migration: V2__ADD_INDEXES.sql
-- ============================================

CREATE INDEX IF NOT EXISTS idx_enrollments_user_id   ON enrollments(user_id);
CREATE INDEX IF NOT EXISTS idx_enrollments_course_id ON enrollments(course_id);
CREATE INDEX IF NOT EXISTS idx_enrollments_status    ON enrollments(status);
