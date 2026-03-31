package com.edusubmit.submissionservice.dto.submission;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GradeSubmissionRequest(
        @NotNull(message = "Grade is required")
        @DecimalMin(value = "0.0", message = "Grade must be at least 0")
        @DecimalMax(value = "100.0", message = "Grade must be at most 100")
        Double grade,

        @Size(max = 2000, message = "Feedback must be at most 2000 characters")
        String feedback
) {
}
