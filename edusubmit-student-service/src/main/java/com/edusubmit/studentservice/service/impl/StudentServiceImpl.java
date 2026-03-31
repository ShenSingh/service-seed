package com.edusubmit.studentservice.service.impl;

import com.edusubmit.studentservice.dto.course.CourseResponse;
import com.edusubmit.studentservice.dto.student.LoginRequest;
import com.edusubmit.studentservice.dto.student.LoginResponse;
import com.edusubmit.studentservice.dto.student.RegisterStudentRequest;
import com.edusubmit.studentservice.dto.student.StudentResponse;
import com.edusubmit.studentservice.entity.Course;
import com.edusubmit.studentservice.entity.Enrollment;
import com.edusubmit.studentservice.entity.User;
import com.edusubmit.studentservice.enums.Role;
import com.edusubmit.studentservice.exception.ConflictException;
import com.edusubmit.studentservice.exception.InvalidCredentialsException;
import com.edusubmit.studentservice.exception.ResourceNotFoundException;
import com.edusubmit.studentservice.repository.CourseRepository;
import com.edusubmit.studentservice.repository.EnrollmentRepository;
import com.edusubmit.studentservice.repository.UserRepository;
import com.edusubmit.studentservice.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(UserRepository userRepository,
                              EnrollmentRepository enrollmentRepository,
                              CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public StudentResponse register(RegisterStudentRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new ConflictException("Email is already registered: " + normalizedEmail);
        }

        User user = new User();
        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setEmail(normalizedEmail);
        user.setPassword(request.password());
        user.setRole(request.role() == null ? Role.STUDENT : request.role());

        User saved = userRepository.save(user);
        return toStudentResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmailIgnoreCase(request.email().trim())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!Objects.equals(user.getPassword(), request.password())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(user.getId(), user.getEmail(), user.getRole(), "Login successful");
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return userRepository.findByRole(Role.STUDENT)
                .stream()
                .map(this::toStudentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getCoursesByStudentId(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        if (student.getRole() != Role.STUDENT) {
            throw new ResourceNotFoundException("User with id " + studentId + " is not a student");
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudentIdOrderByEnrolledAtDesc(studentId);
        if (enrollments.isEmpty()) {
            return List.of();
        }

        List<Long> courseIds = enrollments.stream()
                .map(Enrollment::getCourseId)
                .distinct()
                .toList();

        Map<Long, Course> courseMap = courseRepository.findAllById(courseIds)
                .stream()
                .collect(Collectors.toMap(Course::getId, Function.identity()));

        return enrollments.stream()
                .map(enrollment -> courseMap.get(enrollment.getCourseId()))
                .filter(Objects::nonNull)
                .map(this::toCourseResponse)
                .toList();
    }

    private StudentResponse toStudentResponse(User user) {
        return new StudentResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
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
