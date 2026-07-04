-- ============================================
-- Migration: V1__Create_users_table
-- Description: Crear tabla users y función de trigger
-- Database: userdb (Docker container: postgres-user)
-- ============================================

-- Tabla users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    status VARCHAR(30) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_name_not_empty CHECK (LENGTH(TRIM(full_name)) > 0),
    CONSTRAINT chk_email_format CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Comentarios
COMMENT ON TABLE users IS 'Usuarios del sistema - DB en Docker';
COMMENT ON COLUMN users.id IS 'Identificador único del usuario';