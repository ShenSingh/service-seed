package com.edusubmit.studentservice.dto.enrollment;

import java.time.LocalDateTime;

public record EnrollmentResponse(
        Long id,
        Long studentId,
        Long courseId,
        LocalDateTime enrolledAt
) {
}
