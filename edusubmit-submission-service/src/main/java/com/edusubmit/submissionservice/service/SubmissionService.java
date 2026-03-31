package com.edusubmit.submissionservice.service;

import com.edusubmit.submissionservice.dto.submission.CreateSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.GradeSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.SubmissionResponse;

import java.util.List;

public interface SubmissionService {

    SubmissionResponse createSubmission(CreateSubmissionRequest request);

    List<SubmissionResponse> getSubmissionsByStudentId(String studentId);

    List<SubmissionResponse> getSubmissionsByAssignmentId(String assignmentId);

    SubmissionResponse gradeSubmission(String id, GradeSubmissionRequest request);
}
