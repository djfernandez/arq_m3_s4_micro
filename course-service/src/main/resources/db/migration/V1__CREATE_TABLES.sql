-- ============================================
-- Migration: V1__Create_courses_table
-- Database: coursedb
-- ============================================

CREATE TABLE courses (
    id          BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    published BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_title_not_empty CHECK (LENGTH(TRIM(title)) > 0)
);

COMMENT ON TABLE courses IS 'Cursos del sistema';
