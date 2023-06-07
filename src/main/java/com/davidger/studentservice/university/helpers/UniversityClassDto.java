package com.davidger.studentservice.university.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
public class UniversityClassDto {
    private Long id;
    private String name;
    private Integer courseId;
    private String professor;
    private String description;
}
