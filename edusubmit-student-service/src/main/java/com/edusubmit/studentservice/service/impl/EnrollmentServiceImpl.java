package com.edusubmit.studentservice.service.impl;

import com.edusubmit.studentservice.dto.enrollment.CreateEnrollmentRequest;
import com.edusubmit.studentservice.dto.enrollment.EnrollmentResponse;
import com.edusubmit.studentservice.entity.Course;
import com.edusubmit.studentservice.entity.Enrollment;
import com.edusubmit.studentservice.entity.User;
import com.edusubmit.studentservice.enums.Role;
import com.edusubmit.studentservice.exception.BadRequestException;
import com.edusubmit.studentservice.exception.ConflictException;
import com.edusubmit.studentservice.exception.ResourceNotFoundException;
import com.edusubmit.studentservice.repository.CourseRepository;
import com.edusubmit.studentservice.repository.EnrollmentRepository;
import com.edusubmit.studentservice.repository.UserRepository;
import com.edusubmit.studentservice.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 UserRepository userRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public EnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {
        User student = userRepository.findById(request.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + request.studentId()));

        if (student.getRole() != Role.STUDENT) {
            throw new BadRequestException("User with id " + request.studentId() + " is not a student");
        }

        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + request.courseId()));

        if (enrollmentRepository.existsByStudentIdAndCourseId(request.studentId(), course.getId())) {
            throw new ConflictException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(request.studentId());
        enrollment.setCourseId(course.getId());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(
                saved.getId(),
                saved.getStudentId(),
                saved.getCourseId(),
                saved.getEnrolledAt()
        );
    }
}
