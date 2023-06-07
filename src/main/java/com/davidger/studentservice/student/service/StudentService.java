package com.davidger.studentservice.student.service;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.student.helpers.StudentDto;
import com.davidger.studentservice.student.helpers.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface StudentService {

    Page<StudentDto> getAllStudents(Pageable pageable);

    Optional<StudentDto> getStudentById(Long studentId) throws ResourceNotFoundException;

    void createStudent(@Valid StudentRequest studentRequest);

    void updateStudent(Long studentId, @Valid StudentRequest studentRequest)
            throws ResourceNotFoundException;

    void deleteStudent(Long studentId) throws ResourceNotFoundException;
}
