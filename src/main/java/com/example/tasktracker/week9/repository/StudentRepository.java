package com.example.tasktracker.week9.repository;

import com.example.tasktracker.week9.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByStudentNumber(String studentNumber);
}