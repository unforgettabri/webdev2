package com.example.tasktracker.week9.repository;

import com.example.tasktracker.week9.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}