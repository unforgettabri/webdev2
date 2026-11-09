package com.example.tasktracker.week9;

import com.example.tasktracker.week9.model.*;
import com.example.tasktracker.week9.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Week9RelationshipTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    @Transactional
    void savingDepartmentAlsoSavesEmployees() {
        Department department = new Department("Test Department");
        department.addEmployee(new Employee("Test Employee"));

        Department saved = departmentRepository.saveAndFlush(department);

        assertNotNull(saved.getId());
        assertEquals(1, saved.getEmployees().size());
        assertNotNull(saved.getEmployees().get(0).getId());
    }

    @Test
    @Transactional
    void removingEmployeeFromDepartmentDeletesOrphan() {
        Department department = new Department("Orphan Test");
        Employee employee = new Employee("Employee to Remove");
        department.addEmployee(employee);

        Department savedDepartment =
            departmentRepository.saveAndFlush(department);

        Long employeeId = employee.getId();

        Employee managedEmployee = savedDepartment.getEmployees().get(0);

        savedDepartment.removeEmployee(managedEmployee);
        departmentRepository.flush();

        assertFalse(employeeRepository.existsById(employeeId));
    }

    @Test
    @Transactional
    void duplicateEnrollmentIsRejected() {
        Student student = studentRepository.saveAndFlush(
            new Student("TEST-001", "Test Student")
        );

        Course course = courseRepository.saveAndFlush(
            new Course("TEST101", "Test Course")
        );

        Enrollment first = new Enrollment(LocalDate.now(), "A", course);
        student.addEnrollment(first);
        studentRepository.saveAndFlush(student);

        Enrollment duplicate = new Enrollment(LocalDate.now(), "B", course);
        student.addEnrollment(duplicate);

        assertThrows(
            DataIntegrityViolationException.class,
            () -> studentRepository.saveAndFlush(student)
        );
    }

    @Test
    @Transactional
    void deletingStudentDoesNotDeleteSharedCourse() {
        Course course = courseRepository.saveAndFlush(
            new Course("SHARED101", "Shared Course")
        );

        Student student = studentRepository.saveAndFlush(
            new Student("SHARED-001", "Student One")
        );

        Enrollment enrollment = new Enrollment(LocalDate.now(), null, course);
        student.addEnrollment(enrollment);
        studentRepository.saveAndFlush(student);

        Long studentId = student.getId();
        Long courseId = course.getId();

        studentRepository.deleteById(studentId);
        studentRepository.flush();

        assertFalse(studentRepository.existsById(studentId));
        assertTrue(courseRepository.existsById(courseId));
    }
}