package com.edusubmit.studentservice.dto.student;

import com.edusubmit.studentservice.enums.Role;

public record LoginResponse(
        Long userId,
        String email,
        Role role,
        String message
) {
}
