-- ============================================
-- Migration: V1__Create_notifications_table
-- Database: notificationdb
-- ============================================

CREATE TABLE notifications (
    id         BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    sent BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP    
);

COMMENT ON TABLE notifications IS 'Notificaciones enviadas a usuarios';
