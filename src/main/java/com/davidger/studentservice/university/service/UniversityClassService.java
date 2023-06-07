package com.davidger.studentservice.university.service;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.university.helpers.UniversityClassDto;
import com.davidger.studentservice.university.helpers.UniversityClassRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UniversityClassService {

    List<UniversityClassDto> getAllUniversityClasses(Pageable pageable);

    UniversityClassDto getUniversityClassById(Long id) throws ResourceNotFoundException;

    void createUniversityClass(@Valid UniversityClassRequest universityClassRequest);

    void updateUniversityClass(Long id, @Valid UniversityClassRequest universityClassRequest) throws
            ResourceNotFoundException;

    void deleteUniversityClass(Long id) throws ResourceNotFoundException;
}
