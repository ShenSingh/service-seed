package com.edusubmit.submissionservice.controller;

import com.edusubmit.submissionservice.dto.submission.CreateSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.GradeSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.SubmissionResponse;
import com.edusubmit.submissionservice.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<SubmissionResponse> createSubmission(@Valid @RequestBody CreateSubmissionRequest request) {
        SubmissionResponse response = submissionService.createSubmission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudentId(studentId));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByAssignment(@PathVariable String assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignmentId(assignmentId));
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<SubmissionResponse> gradeSubmission(@PathVariable String id,
                                                              @Valid @RequestBody GradeSubmissionRequest request) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, request));
    }
}
