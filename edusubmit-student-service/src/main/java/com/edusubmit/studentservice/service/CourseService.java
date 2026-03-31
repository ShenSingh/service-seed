package com.edusubmit.studentservice.service;

import com.edusubmit.studentservice.dto.course.CourseResponse;
import com.edusubmit.studentservice.dto.course.CreateCourseRequest;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    List<CourseResponse> getAllCourses();
}
