package com.davidger.studentservice.student.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StudentRequest(
        @NotBlank(message = "name must not be blank") String name,
        @NotNull(message = "studentId must not be null") Integer studentId
) {
}
