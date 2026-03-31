package com.edusubmit.submissionservice.dto.assignment;

import java.time.LocalDateTime;

public record AssignmentResponse(
        String id,
        String courseId,
        String title,
        String description,
        LocalDateTime dueDate,
        String createdBy,
        LocalDateTime createdAt
) {
}
