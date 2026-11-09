package com.example.tasktracker.week9;

import com.example.tasktracker.week9.service.Week9Service;
import com.example.tasktracker.week9.capstone.ClinicService;
import com.example.tasktracker.week9.model.Employee;
import com.example.tasktracker.week9.repository.DepartmentRepository;
import org.hibernate.LazyInitializationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Week9DemoRunner implements CommandLineRunner {

    private final Week9Service week9Service;
    private final DepartmentRepository departmentRepository;
    private final ClinicService clinicService;

    public Week9DemoRunner(
        Week9Service week9Service,
        DepartmentRepository departmentRepository,
        ClinicService clinicService
    ) {
        this.week9Service = week9Service;
        this.departmentRepository = departmentRepository;
        this.clinicService = clinicService;
    }

    @Override
    public void run(String... args) {
        if (!departmentRepository.existsByName("Information Technology")) {
            week9Service.createDepartmentWithEmployees(
                "Information Technology",
                "Taylor Morgan",
                "Jordan Lee"
            );
        }

        if (departmentRepository.count() > 0) {
            var department = departmentRepository
                .findWithEmployeesByName("Information Technology")
                .orElseThrow();
            if (!department.getEmployees().isEmpty()) {
                Long employeeId = department.getEmployees().get(0).getId();

                try {
                    Employee employee =
                        week9Service.getEmployeeOutsideTransaction(employeeId);
                    employee.getDepartment().getName();
                    System.out.println("Unexpected: lazy relation was already loaded.");
                } catch (LazyInitializationException exception) {
                    System.out.println("Lazy loading demonstration: LazyInitializationException reproduced.");
                }

                System.out.println(
                    "Transactional fetch: "
                    + week9Service.getEmployeeDepartmentTransactional(employeeId)
                );

                System.out.println(
                    "Fetch join: "
                    + week9Service.getEmployeeDepartmentWithFetchJoin(employeeId)
                );
            }
        }

        clinicService.createSampleClinicRecords();
        System.out.println("Capstone graph: " + clinicService.readSampleClinicRecords());
    }
}