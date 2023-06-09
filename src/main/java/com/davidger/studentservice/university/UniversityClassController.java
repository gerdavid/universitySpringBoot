package com.davidger.studentservice.university;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.university.helpers.UniversityClassDto;
import com.davidger.studentservice.university.helpers.UniversityClassRequest;
import com.davidger.studentservice.university.service.UniversityClassImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university-classes")
@RequiredArgsConstructor
public class UniversityClassController {

    private final UniversityClassImpl universityClassImpl;

    @GetMapping
    public ResponseEntity<List<UniversityClassDto>> getAllUniversityClasses(Pageable pageable) {
        List<UniversityClassDto> universityClassDto = universityClassImpl.getAllUniversityClasses(pageable);//you can also use var
        return ResponseEntity.ok(universityClassDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityClassDto> getUniversityClassById(@PathVariable(value = "id") Long universityClassId)
            throws ResourceNotFoundException {
        var UniversityClass = universityClassImpl.getUniversityClassById(universityClassId);
        return ResponseEntity.ok(UniversityClass);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUniversityClass(@Valid @RequestBody UniversityClassRequest universityClassRequest) {
        universityClassImpl.createUniversityClass(universityClassRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUniversityClass(@PathVariable(value = "id") Long universityClassId, @Valid @RequestBody UniversityClassRequest universityClassRequest)
            throws ResourceNotFoundException {
        universityClassImpl.updateUniversityClass(universityClassId, universityClassRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUniversityClass(@PathVariable(value = "id") Long universityClassId) throws ResourceNotFoundException {
        universityClassImpl.deleteUniversityClass(universityClassId);
    }
}
