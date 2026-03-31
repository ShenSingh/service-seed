package com.edusubmit.studentservice.controller;

import com.edusubmit.studentservice.dto.course.CourseResponse;
import com.edusubmit.studentservice.dto.student.LoginRequest;
import com.edusubmit.studentservice.dto.student.LoginResponse;
import com.edusubmit.studentservice.dto.student.RegisterStudentRequest;
import com.edusubmit.studentservice.dto.student.StudentResponse;
import com.edusubmit.studentservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<StudentResponse> register(@Valid @RequestBody RegisterStudentRequest request) {
        System.out.println("Reg");
        StudentResponse response = studentService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = studentService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseResponse>> getCoursesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getCoursesByStudentId(studentId));
    }
}
