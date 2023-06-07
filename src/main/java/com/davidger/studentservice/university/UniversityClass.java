package com.davidger.studentservice.university;

import jakarta.persistence.*;
import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
@Table(name = "university")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
