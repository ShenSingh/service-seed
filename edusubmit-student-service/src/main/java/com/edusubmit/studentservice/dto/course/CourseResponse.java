package com.edusubmit.studentservice.dto.course;

import java.time.LocalDateTime;

public record CourseResponse(
        Long id,
        String code,
        String title,
        String description,
        Long lecturerId,
        LocalDateTime createdAt
) {
}
