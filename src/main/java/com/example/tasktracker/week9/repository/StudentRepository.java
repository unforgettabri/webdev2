package com.example.tasktracker.week9.repository;

import com.example.tasktracker.week9.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        SELECT e
        FROM Employee e
        JOIN FETCH e.department
        WHERE e.id = :id
    """)
    Optional<Employee> findWithDepartmentById(@Param("id") Long id);
}