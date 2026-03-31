package com.edusubmit.submissionservice.dto.submission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSubmissionRequest(
        @NotBlank(message = "Assignment id is required")
        String assignmentId,

        @NotBlank(message = "Student id is required")
        String studentId,

        @NotBlank(message = "Course id is required")
        String courseId,

        @NotBlank(message = "File id is required")
        String fileId,

        @NotBlank(message = "File name is required")
        @Size(max = 255, message = "File name must be at most 255 characters")
        String fileName
) {
}
