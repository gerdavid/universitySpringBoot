package com.davidger.studentservice.student;

import com.davidger.studentservice.university.UniversityClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer studentId;

    @ManyToMany(fetch=FetchType.LAZY, cascade ={CascadeType.MERGE} )
    @JoinTable(name ="student_university_class", joinColumns = {@JoinColumn(name = "student_id")},
     inverseJoinColumns = {@JoinColumn(name = "university_class_id")})
    private Set<UniversityClass> universityClasses= new HashSet<>();
}
