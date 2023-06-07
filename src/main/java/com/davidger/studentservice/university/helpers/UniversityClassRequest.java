package com.davidger.studentservice.university.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UniversityClassRequest(
    @NotBlank(message = "name must not be null") String name,
    @NotNull(message = "courseId must not be null") Integer courseId,
    @NotBlank(message = "professor must not be null") String professor,
    @NotBlank(message = "description must not be null") String description
){
}
