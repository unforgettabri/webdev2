package com.example.tasktracker.week9.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "week9_courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private String title;

    // Inverse side of the initial direct many-to-many mapping.
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Final association-entity approach.
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course() {}

    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}