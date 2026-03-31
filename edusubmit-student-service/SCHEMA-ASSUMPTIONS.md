# Schema Assumptions

## Database

- Database name: `edusubmit_student_db`
- Engine: MySQL 8+
- Character set: UTF-8 (`utf8mb4` recommended)

## Table Assumptions

1. `app_users`
- `id` is `BIGINT` auto-increment primary key.
- `email` is unique.
- `role` stored as `VARCHAR` (`STUDENT` or `LECTURER`).
- `created_at` stored as timestamp.

2. `courses`
- `id` is `BIGINT` auto-increment primary key.
- `code` is unique (course code).
- `lecturer_id` references a user id with role `LECTURER` (enforced at service layer).
- `created_at` stored as timestamp.

3. `enrollments`
- `id` is `BIGINT` auto-increment primary key.
- `student_id` stores user id with role `STUDENT` (enforced at service layer).
- `course_id` stores a course id.
- Unique pair constraint on (`student_id`, `course_id`) prevents duplicate enrollment.
- `enrolled_at` stored as timestamp.

## Integrity Notes

- This sample uses ID-based relations (`studentId`, `courseId`, `lecturerId`) with validation in services.
- Foreign keys can be added later if strict DB-level constraints are required.
