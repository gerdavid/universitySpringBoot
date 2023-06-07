package com.davidger.studentservice.university.service;

import com.davidger.studentservice.exception.ResourceNotFoundException;
import com.davidger.studentservice.university.UniversityClass;
import com.davidger.studentservice.university.helpers.UniversityClassDto;
import com.davidger.studentservice.university.helpers.UniversityClassMapper;
import com.davidger.studentservice.university.helpers.UniversityClassRepository;
import com.davidger.studentservice.university.helpers.UniversityClassRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UniversityClassImpl implements UniversityClassService{

    private final UniversityClassRepository universityClassRepository;
    private final UniversityClassMapper universityClassMapper;
    @Override
    public List<UniversityClassDto> getAllUniversityClasses(Pageable pageable) {
        return universityClassRepository
                .findAll(pageable)
                .stream()
                .map(universityClassMapper)
                .toList();
    }

    @Override
    public UniversityClassDto getUniversityClassById(Long universityClassId) throws ResourceNotFoundException {
        requireNonNull(universityClassId,"UniversityClassId must not be null");
        return universityClassRepository
                .findById(universityClassId)
                .map(universityClassMapper)
                .orElseThrow(()->new ResourceNotFoundException("University not found by this ID: "+universityClassId));
    }

    @Override
    public void createUniversityClass(@Valid UniversityClassRequest universityClassRequest) {
        var universityClass= UniversityClass.builder()
                .name(universityClassRequest.name())
                .courseId(universityClassRequest.courseId())
                .professor(universityClassRequest.professor())
                .description(universityClassRequest.description())
                .build();
        universityClassRepository.save(universityClass);
    }

    @Override @Transactional
    public void updateUniversityClass(Long universityClassId, @Valid UniversityClassRequest universityClassRequest)
            throws ResourceNotFoundException {
        requireNonNull(universityClassId,"UniversityClassId must not be null");
        requireNonNull(universityClassRequest,"universityClassRequest must not be null");
        var universityClass= universityClassRepository.findById(universityClassId)
                .orElseThrow(()-> new ResourceNotFoundException("University Class not found by this ID: "
                +universityClassId));

        updateUniversityClassRequest(universityClass,universityClassRequest);
        universityClassRepository.save(universityClass);
    }

    @Override @Transactional
    public void deleteUniversityClass(Long universityClassId) throws ResourceNotFoundException {
        requireNonNull(universityClassId,"UniversityClassId must not be null");
        var universityClass=universityClassRepository.findById(universityClassId)
                .orElseThrow(()->new ResourceNotFoundException("University Class not found by this ID: "
                        +universityClassId));
        universityClassRepository.delete(universityClass);
    }

    private void updateUniversityClassRequest(UniversityClass universityClass, UniversityClassRequest
            universityClassRequest){
        updateIfNotNull(universityClassRequest::name,universityClass::setName);
        updateIfNotNull(universityClassRequest::courseId,universityClass::setCourseId);
        updateIfNotNull(universityClassRequest::professor,universityClass::setProfessor);
        updateIfNotNull(universityClassRequest::description,universityClass::setDescription);
    }
    private <T> void updateIfNotNull(Supplier<T> valueSupplier, Consumer<T> valueConsumer){
        ofNullable(valueSupplier.get()).ifPresent(valueConsumer);
    }
}
