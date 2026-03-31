package com.edusubmit.studentservice.dto.enrollment;

import jakarta.validation.constraints.NotNull;

public record CreateEnrollmentRequest(
        @NotNull(message = "Student id is required")
        Long studentId,

        @NotNull(message = "Course id is required")
        Long courseId
) {
}
