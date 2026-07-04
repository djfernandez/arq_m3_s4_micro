-- ============================================
-- Migration: V2__ADD_INDEXES.sql
-- ============================================

CREATE UNIQUE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_full_name ON users(full_name);
CREATE INDEX idx_users_created_at ON users(created_at DESC);