package com.edusubmit.studentservice.dto.student;

import com.edusubmit.studentservice.enums.Role;

import java.time.LocalDateTime;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}
