-- ============================================
-- Migration: V1__Create_enrollments_table
-- Database: enrollmentdb
-- ============================================

CREATE TABLE enrollments (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    course_id   BIGINT NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    enrolled_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_status CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED')),
    CONSTRAINT uq_user_course UNIQUE (user_id, course_id)
);

COMMENT ON TABLE enrollments IS 'Matrículas de usuarios en cursos';
