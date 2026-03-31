package com.edusubmit.submissionservice.service.impl;

import com.edusubmit.submissionservice.document.Assignment;
import com.edusubmit.submissionservice.document.Submission;
import com.edusubmit.submissionservice.dto.submission.CreateSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.GradeSubmissionRequest;
import com.edusubmit.submissionservice.dto.submission.SubmissionResponse;
import com.edusubmit.submissionservice.enums.SubmissionStatus;
import com.edusubmit.submissionservice.exception.BadRequestException;
import com.edusubmit.submissionservice.exception.ResourceNotFoundException;
import com.edusubmit.submissionservice.repository.AssignmentRepository;
import com.edusubmit.submissionservice.repository.SubmissionRepository;
import com.edusubmit.submissionservice.service.SubmissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
                                 AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public SubmissionResponse createSubmission(CreateSubmissionRequest request) {
        Assignment assignment = assignmentRepository.findById(request.assignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + request.assignmentId()));

        if (!assignment.getCourseId().equals(request.courseId())) {
            throw new BadRequestException("Course id does not match the assignment's course");
        }

        Submission submission = new Submission();
        submission.setAssignmentId(request.assignmentId().trim());
        submission.setStudentId(request.studentId().trim());
        submission.setCourseId(request.courseId().trim());
        submission.setFileId(request.fileId().trim());
        submission.setFileName(request.fileName().trim());
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(SubmissionStatus.SUBMITTED);
        submission.setGrade(null);
        submission.setFeedback(null);

        Submission saved = submissionRepository.save(submission);
        return toResponse(saved);
    }

    @Override
    public List<SubmissionResponse> getSubmissionsByStudentId(String studentId) {
        return submissionRepository.findByStudentIdOrderBySubmittedAtDesc(studentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<SubmissionResponse> getSubmissionsByAssignmentId(String assignmentId) {
        return submissionRepository.findByAssignmentIdOrderBySubmittedAtDesc(assignmentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public SubmissionResponse gradeSubmission(String id, GradeSubmissionRequest request) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));

        submission.setGrade(request.grade());
        submission.setFeedback(request.feedback() == null ? null : request.feedback().trim());
        submission.setStatus(SubmissionStatus.GRADED);

        Submission updated = submissionRepository.save(submission);
        return toResponse(updated);
    }

    private SubmissionResponse toResponse(Submission submission) {
        return new SubmissionResponse(
                submission.getId(),
                submission.getAssignmentId(),
                submission.getStudentId(),
                submission.getCourseId(),
                submission.getFileId(),
                submission.getFileName(),
                submission.getSubmittedAt(),
                submission.getStatus(),
                submission.getGrade(),
                submission.getFeedback()
        );
    }
}
