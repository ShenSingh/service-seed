package com.edusubmit.studentservice.repository;

import com.edusubmit.studentservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCodeIgnoreCase(String code);

    List<Course> findAllByOrderByCreatedAtDesc();
}
