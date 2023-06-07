package com.davidger.studentservice.university.helpers;

import com.davidger.studentservice.university.UniversityClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityClassRepository extends JpaRepository<UniversityClass,Long> {
}
