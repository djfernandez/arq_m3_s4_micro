-- ============================================
-- Migration: V2__ADD_INDEXES.sql
-- ============================================

CREATE INDEX idx_notifications_user_id ON notifications(user_id);
CREATE INDEX idx_notifications_read    ON notifications(read);
CREATE INDEX idx_notifications_type    ON notifications(type);
CREATE INDEX idx_notifications_sent_at ON notifications(sent_at DESC);
