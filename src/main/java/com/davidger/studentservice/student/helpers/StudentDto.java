package com.davidger.studentservice.student.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder @AllArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private Integer studentId;
}
