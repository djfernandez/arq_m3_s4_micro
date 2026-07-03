-- ============================================
-- Migration: V2__ADD_INDEXES.sql
-- ============================================

CREATE UNIQUE INDEX idx_courses_title      ON courses(title);
CREATE INDEX        idx_courses_instructor ON courses(instructor);
CREATE INDEX        idx_courses_created_at ON courses(created_at DESC);
