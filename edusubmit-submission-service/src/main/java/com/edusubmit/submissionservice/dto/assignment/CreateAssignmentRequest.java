package com.edusubmit.submissionservice.dto.assignment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateAssignmentRequest(
        @NotBlank(message = "Course id is required")
        @Size(max = 100, message = "Course id must be at most 100 characters")
        String courseId,

        @NotBlank(message = "Title is required")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 3000, message = "Description must be at most 3000 characters")
        String description,

        @NotNull(message = "Due date is required")
        @Future(message = "Due date must be in the future")
        LocalDateTime dueDate,

        @NotBlank(message = "Created by is required")
        @Size(max = 100, message = "Created by must be at most 100 characters")
        String createdBy
) {
}
