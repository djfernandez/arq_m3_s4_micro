-- ============================================
-- Migration: V1__Create_courses_table
-- Database: coursedb
-- ============================================

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TABLE courses (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    instructor  VARCHAR(100) NOT NULL,
    price       NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_title_not_empty CHECK (LENGTH(TRIM(title)) > 0),
    CONSTRAINT chk_price_positive  CHECK (price >= 0)
);

CREATE TRIGGER update_courses_updated_at
    BEFORE UPDATE ON courses
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

COMMENT ON TABLE courses IS 'Cursos del sistema';
