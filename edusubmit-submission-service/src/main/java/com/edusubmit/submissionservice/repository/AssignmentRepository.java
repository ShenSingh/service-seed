package com.edusubmit.submissionservice.repository;

import com.edusubmit.submissionservice.document.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {

    List<Assignment> findAllByOrderByCreatedAtDesc();

    List<Assignment> findByCourseIdOrderByCreatedAtDesc(String courseId);
}
