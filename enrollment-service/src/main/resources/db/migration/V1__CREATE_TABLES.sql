-- ============================================
-- Migration: V1__Create_enrollments_table
-- Database: enrollmentdb
-- ============================================

CREATE TABLE enrollments (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    course_id   BIGINT NOT NULL,
    status      VARCHAR(40) DEFAULT 'PENDING_PAYMENT',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_status CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED')),
    CONSTRAINT uq_user_course UNIQUE (user_id, course_id)
);

COMMENT ON TABLE enrollments IS 'Matrículas de usuarios en cursos';
