package com.edusubmit.submissionservice.repository;

import com.edusubmit.submissionservice.document.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubmissionRepository extends MongoRepository<Submission, String> {

    List<Submission> findByStudentIdOrderBySubmittedAtDesc(String studentId);

    List<Submission> findByAssignmentIdOrderBySubmittedAtDesc(String assignmentId);
}
