-- ============================================
-- Migration: V1__Create_payments_table
-- Database: paymentdb
-- ============================================

CREATE TABLE payments (
    id            BIGSERIAL PRIMARY KEY,
    enrollment_id BIGINT NOT NULL,
    amount        NUMERIC(10, 2) NOT NULL,
    status        VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    method        VARCHAR(30),
    paid_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_payment_status CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')),
    CONSTRAINT chk_amount_positive CHECK (amount > 0)
);

COMMENT ON TABLE payments IS 'Pagos de matrículas';
