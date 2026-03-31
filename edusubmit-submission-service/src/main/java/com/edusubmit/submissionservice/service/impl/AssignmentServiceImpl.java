package com.edusubmit.submissionservice.service.impl;

import com.edusubmit.submissionservice.document.Assignment;
import com.edusubmit.submissionservice.dto.assignment.AssignmentResponse;
import com.edusubmit.submissionservice.dto.assignment.CreateAssignmentRequest;
import com.edusubmit.submissionservice.repository.AssignmentRepository;
import com.edusubmit.submissionservice.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public AssignmentResponse createAssignment(CreateAssignmentRequest request) {
        Assignment assignment = new Assignment();
        assignment.setCourseId(request.courseId().trim());
        assignment.setTitle(request.title().trim());
        assignment.setDescription(request.description().trim());
        assignment.setDueDate(request.dueDate());
        assignment.setCreatedBy(request.createdBy().trim());
        assignment.setCreatedAt(LocalDateTime.now());

        Assignment saved = assignmentRepository.save(assignment);
        return toResponse(saved);
    }

    @Override
    public List<AssignmentResponse> getAllAssignments() {
        return assignmentRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<AssignmentResponse> getAssignmentsByCourseId(String courseId) {
        return assignmentRepository.findByCourseIdOrderByCreatedAtDesc(courseId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AssignmentResponse toResponse(Assignment assignment) {
        return new AssignmentResponse(
                assignment.getId(),
                assignment.getCourseId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getDueDate(),
                assignment.getCreatedBy(),
                assignment.getCreatedAt()
        );
    }
}
