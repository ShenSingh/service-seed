package com.edusubmit.studentservice.service;

import com.edusubmit.studentservice.dto.course.CourseResponse;
import com.edusubmit.studentservice.dto.student.LoginRequest;
import com.edusubmit.studentservice.dto.student.LoginResponse;
import com.edusubmit.studentservice.dto.student.RegisterStudentRequest;
import com.edusubmit.studentservice.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse register(RegisterStudentRequest request);

    LoginResponse login(LoginRequest request);

    List<StudentResponse> getAllStudents();

    List<CourseResponse> getCoursesByStudentId(Long studentId);
}
