package com.edusubmit.studentservice.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCourseRequest(
        @NotBlank(message = "Course code is required")
        @Size(max = 50, message = "Course code must be at most 50 characters")
        String code,

        @NotBlank(message = "Course title is required")
        @Size(max = 255, message = "Course title must be at most 255 characters")
        String title,

        @NotBlank(message = "Course description is required")
        @Size(max = 2000, message = "Course description must be at most 2000 characters")
        String description,

        @NotNull(message = "Lecturer id is required")
        Long lecturerId
) {
}
