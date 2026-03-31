package com.edusubmit.studentservice.service.impl;

import com.edusubmit.studentservice.dto.course.CourseResponse;
import com.edusubmit.studentservice.dto.course.CreateCourseRequest;
import com.edusubmit.studentservice.entity.Course;
import com.edusubmit.studentservice.entity.User;
import com.edusubmit.studentservice.enums.Role;
import com.edusubmit.studentservice.exception.BadRequestException;
import com.edusubmit.studentservice.exception.ConflictException;
import com.edusubmit.studentservice.exception.ResourceNotFoundException;
import com.edusubmit.studentservice.repository.CourseRepository;
import com.edusubmit.studentservice.repository.UserRepository;
import com.edusubmit.studentservice.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CourseResponse createCourse(CreateCourseRequest request) {
        if (courseRepository.existsByCodeIgnoreCase(request.code().trim())) {
            throw new ConflictException("Course code already exists: " + request.code().trim());
        }

        User lecturer = userRepository.findById(request.lecturerId())
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer not found with id: " + request.lecturerId()));

        if (lecturer.getRole() != Role.LECTURER) {
            throw new BadRequestException("User with id " + request.lecturerId() + " is not a lecturer");
        }

        Course course = new Course();
        course.setCode(request.code().trim());
        course.setTitle(request.title().trim());
        course.setDescription(request.description().trim());
        course.setLecturerId(request.lecturerId());

        Course saved = courseRepository.save(course);
        return toCourseResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toCourseResponse)
                .toList();
    }

    private CourseResponse toCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getDescription(),
                course.getLecturerId(),
                course.getCreatedAt()
        );
    }
}
