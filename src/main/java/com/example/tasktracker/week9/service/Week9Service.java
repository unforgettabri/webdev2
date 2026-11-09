package com.example.tasktracker.week9.service;

import com.example.tasktracker.week9.model.*;
import com.example.tasktracker.week9.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Week9Service {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public Week9Service(
        UserRepository userRepository,
        DepartmentRepository departmentRepository,
        EmployeeRepository employeeRepository,
        StudentRepository studentRepository,
        CourseRepository courseRepository,
        EnrollmentRepository enrollmentRepository
    ) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public User createUserWithProfile(String username, String fullName, String bio) {
        User user = new User(username);
        UserProfile profile = new UserProfile(fullName, bio);
        user.setProfile(profile);
        return userRepository.save(user);
    }

    @Transactional
    public Department createDepartmentWithEmployees(String departmentName, String... names) {
        Department department = new Department(departmentName);

        for (String name : names) {
            department.addEmployee(new Employee(name));
        }

        return departmentRepository.save(department);
    }

    // Intentionally not transactional: used to demonstrate lazy-loading failure.
    public Employee getEmployeeOutsideTransaction(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
    }

    // The department is accessed while the persistence context is active.
    @Transactional(readOnly = true)
    public String getEmployeeDepartmentTransactional(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        return employee.getDepartment().getName();
    }

    // Fetch join loads the employee and department in one query.
    @Transactional(readOnly = true)
    public String getEmployeeDepartmentWithFetchJoin(Long id) {
        Employee employee = employeeRepository.findWithDepartmentById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        return employee.getDepartment().getName();
    }

    @Transactional
    public void removeEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Employee employee = department.getEmployees().stream()
            .filter(item -> item.getId().equals(employeeId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        department.removeEmployee(employee);
        departmentRepository.save(department);
    }

    @Transactional
    public Student createStudentWithEnrollment(
        String studentNumber,
        String studentName,
        String courseCode,
        String courseTitle
    ) {
        Student student = new Student(studentNumber, studentName);
        Course course = courseRepository.findAll().stream()
            .filter(item -> item.getCourseCode().equals(courseCode))
            .findFirst()
            .orElseGet(() -> courseRepository.save(new Course(courseCode, courseTitle)));

        Enrollment enrollment = new Enrollment(
            java.time.LocalDate.now(),
            null,
            course
        );

        student.addEnrollment(enrollment);
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public long countEnrollments() {
        return enrollmentRepository.count();
    }
}