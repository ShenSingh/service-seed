package com.edusubmit.submissionservice.dto.submission;

import com.edusubmit.submissionservice.enums.SubmissionStatus;

import java.time.LocalDateTime;

public record SubmissionResponse(
        String id,
        String assignmentId,
        String studentId,
        String courseId,
        String fileId,
        String fileName,
        LocalDateTime submittedAt,
        SubmissionStatus status,
        Double grade,
        String feedback
) {
}
