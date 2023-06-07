package com.davidger.studentservice.university.helpers;

import com.davidger.studentservice.university.UniversityClass;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UniversityClassMapper implements Function<UniversityClass, UniversityClassDto> {
    @Override
    public UniversityClassDto apply(UniversityClass universityClass) {
        return UniversityClassDto.builder()
                .id(universityClass.getId())
                .name(universityClass.getName())
                .courseId(universityClass.getCourseId())
                .professor(universityClass.getProfessor())
                .description(universityClass.getDescription())
                .build();
    }
}
