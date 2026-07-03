-- ============================================
-- Migration: V1__Create_notifications_table
-- Database: notificationdb
-- ============================================

CREATE TABLE notifications (
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    type       VARCHAR(20) NOT NULL,
    subject    VARCHAR(200),
    message    VARCHAR(1000) NOT NULL,
    read       BOOLEAN NOT NULL DEFAULT FALSE,
    sent_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_notification_type CHECK (type IN ('EMAIL', 'SMS', 'PUSH'))
);

COMMENT ON TABLE notifications IS 'Notificaciones enviadas a usuarios';
