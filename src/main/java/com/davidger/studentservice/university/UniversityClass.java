package com.davidger.studentservice.university;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
@Table(name = "university")
public class UniversityClass {
}
