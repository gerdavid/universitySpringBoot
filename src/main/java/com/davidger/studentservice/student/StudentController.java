package com.davidger.studentservice.student;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.student.helpers.StudentDto;
import com.davidger.studentservice.student.helpers.StudentRequest;
import com.davidger.studentservice.student.service.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController @AllArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;

    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @PageableDefault(size = 20, page = 0,sort = "name")
            Pageable pageable) {
       Page<StudentDto> students = studentServiceImpl.getAllStudents(pageable);
       return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentDto>> getStudentById(@PathVariable(value = "id") Long id)
    throws ResourceNotFoundException {
        Optional<StudentDto> studentDto= studentServiceImpl.getStudentById(id);
        return ResponseEntity.ok().body(studentDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody @Valid StudentRequest studentRequest){
        studentServiceImpl.createStudent(studentRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable(value = "id") Long id, @RequestBody @Valid StudentRequest studentRequest) throws
            ResourceNotFoundException{
        studentServiceImpl.updateStudent(id,studentRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        studentServiceImpl.deleteStudent(id);
    }
}
