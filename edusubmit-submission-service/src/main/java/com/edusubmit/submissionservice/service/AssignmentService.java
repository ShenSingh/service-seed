package com.edusubmit.submissionservice.service;

import com.edusubmit.submissionservice.dto.assignment.AssignmentResponse;
import com.edusubmit.submissionservice.dto.assignment.CreateAssignmentRequest;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment(CreateAssignmentRequest request);

    List<AssignmentResponse> getAllAssignments();

    List<AssignmentResponse> getAssignmentsByCourseId(String courseId);
}
