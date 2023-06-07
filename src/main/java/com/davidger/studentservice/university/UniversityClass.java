package com.davidger.studentservice.university;

import com.davidger.studentservice.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
@Table(name = "university")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer courseId;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false)
    private String description;

    @JsonManagedReference
    @ManyToMany(mappedBy = "universityClasses", fetch = FetchType.EAGER)
    private Set<Student> student= new HashSet<>();
}
