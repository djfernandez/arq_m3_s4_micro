-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO enrollments (user_id, course_id, status)
SELECT 1, 1, 'ACTIVE'
WHERE NOT EXISTS (
	SELECT 1 FROM enrollments WHERE user_id = 1 AND course_id = 1
);

INSERT INTO enrollments (user_id, course_id, status)
SELECT 1, 2, 'ACTIVE'
WHERE NOT EXISTS (
	SELECT 1 FROM enrollments WHERE user_id = 1 AND course_id = 2
);

INSERT INTO enrollments (user_id, course_id, status)
SELECT 2, 1, 'COMPLETED'
WHERE NOT EXISTS (
	SELECT 1 FROM enrollments WHERE user_id = 2 AND course_id = 1
);

INSERT INTO enrollments (user_id, course_id, status)
SELECT 3, 3, 'ACTIVE'
WHERE NOT EXISTS (
	SELECT 1 FROM enrollments WHERE user_id = 3 AND course_id = 3
);

INSERT INTO enrollments (user_id, course_id, status)
SELECT 4, 2, 'CANCELLED'
WHERE NOT EXISTS (
	SELECT 1 FROM enrollments WHERE user_id = 4 AND course_id = 2
);
