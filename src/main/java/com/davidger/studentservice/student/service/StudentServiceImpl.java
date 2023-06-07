package com.davidger.studentservice.student.service;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.student.Student;
import com.davidger.studentservice.student.StudentRepository;
import com.davidger.studentservice.student.helpers.StudentDto;
import com.davidger.studentservice.student.helpers.StudentMapper;
import com.davidger.studentservice.student.helpers.StudentRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;


@Service @AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        var students= studentRepository.findAll(pageable);
        return students.map(studentMapper);
    }

    @Override
    public Optional<StudentDto> getStudentById(Long studentId) throws ResourceNotFoundException {
        requireNonNull(studentId,"Student ID must not be null");


        return ofNullable(studentRepository.findById(studentId)
                .map(studentMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for ID "+ studentId)));

    }

    @Override @Transactional
    public void createStudent(StudentRequest studentRequest) {
        Student student= Student.builder()
                .name(studentRequest.name())
                .studentId(studentRequest.studentId())
                .build();
        studentRepository.save(student);
    }

    @Override @Transactional
    public void updateStudent(Long studentId, StudentRequest studentRequest) throws ResourceNotFoundException {
        requireNonNull(studentId,"Student ID must not be null");
        requireNonNull(studentRequest,"Student Request must not be null");

        var student= studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for ID "+studentId));

        ofNullable(studentRequest.name())
                .ifPresent(student::setName);
        ofNullable(studentRequest.studentId())
                .ifPresent(student::setStudentId);

        studentRepository.save(student);
    }

    @Override @Transactional
    public void deleteStudent(Long studentId) throws ResourceNotFoundException {
        requireNonNull(studentId,"Student ID must not be null");
        var student= studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for ID "+studentId));
        studentRepository.delete(student);
    }
}
