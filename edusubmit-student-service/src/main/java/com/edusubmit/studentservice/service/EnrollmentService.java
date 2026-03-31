package com.edusubmit.studentservice.service;

import com.edusubmit.studentservice.dto.enrollment.CreateEnrollmentRequest;
import com.edusubmit.studentservice.dto.enrollment.EnrollmentResponse;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(CreateEnrollmentRequest request);
}
