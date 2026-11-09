package com.example.tasktracker.week9.repository;

import com.example.tasktracker.week9.model.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = "employees")
    Optional<Department> findWithEmployeesByName(String name);
}