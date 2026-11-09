# Lab 9: Capstone Milestone 2 — Database Integration and JPA Entity Model

## Task 1: One-to-One and One-to-Many Relationships

### One-to-One: User and UserProfile
- User and UserProfile use a bidirectional one-to-one relationship.
- User is the owning side.
- The foreign key is `profile_id` in the `week9_users` table.
- UserProfile is the inverse side through `mappedBy = "profile"`.
- Cascade ALL and orphan removal are configured for the profile because it belongs exclusively to its User.

### One-to-Many: Department and Employee
- One Department can have many Employees.
- Employee is the owning side through `department_id`.
- Department is the inverse side through `mappedBy = "department"`.
- Cascade PERSIST and MERGE allow employees to be saved with their department.
- Orphan removal deletes an employee removed from the department collection.
- Helper methods maintain both sides of the relationship.

## Task 2: Many-to-Many and Association Entity

### Direct Student-Course Mapping
- Student is the owning side.
- Course is the inverse side.
- The join table is `student_course`.
- Its foreign keys are `student_id` and `course_id`.

### Enrollment Association Entity
The improved model uses Enrollment because the relationship includes additional information:
- enrollmentDate
- grade
- student_id
- course_id

Enrollment has Many-to-One relationships with Student and Course.
Student and Course each have One-to-Many relationships with Enrollment.

A database unique constraint on `student_id` and `course_id` prevents duplicate enrollment.

The direct mapping is retained to demonstrate the initial approach. Enrollment is used for the richer relationship model.

## Task 3: Fetch Strategies

- Many-to-One relationships use LAZY fetching.
- Collections use LAZY fetching by default.
- Accessing a lazy relationship after the persistence context closes can cause LazyInitializationException.
- A transactional service method fixes this by accessing the relationship while the persistence context is active.
- A JPQL JOIN FETCH query retrieves the Employee and Department together.

### Query behavior
- Lazy loading may execute an additional query when the relationship is accessed.
- A fetch join retrieves the requested relationship in the same query.
- EAGER fetching is not used as a blanket solution because it can retrieve unnecessary data and increase query costs.

## Task 4: Cascade and Orphan Removal

### Department and Employee
- PERSIST and MERGE cascade from Department to Employee.
- orphanRemoval is enabled.
- Removing an employee from the Department collection and saving the Department deletes that employee.

### Student and Course
- Enrollment is the dependent association entity.
- Student-to-Enrollment uses PERSIST and MERGE with orphan removal.
- Course is shared and is not configured for cascading REMOVE.
- Deleting a Student must not delete a shared Course.

## Task 5: Veterinary Clinic Management System

### Entities
1. Owner
2. ClinicPet
3. Veterinarian
4. Appointment
5. VaccinationRecord

### Relationships and Foreign Keys

| Relationship | Cardinality | Foreign Key / Mapping |
|---|---|---|
| Owner to ClinicPet | One-to-Many | owner_id in clinic_pets |
| ClinicPet to Appointment | One-to-Many | pet_id in clinic_appointments |
| Veterinarian to Appointment | One-to-Many | vet_id in clinic_appointments |
| ClinicPet to VaccinationRecord | One-to-Many | pet_id in clinic_vaccination_records |

Appointment acts as an association entity between ClinicPet and Veterinarian. It stores appointment_date and reason_for_visit.

VaccinationRecord uses a composite primary key consisting of pet_id, vaccine_name, and vaccination_date.

### Ownership
- ClinicPet owns the Owner relationship through owner_id.
- Appointment owns the Pet and Veterinarian relationships through pet_id and vet_id.
- VaccinationRecord owns its Pet relationship through pet_id.
- Owner, ClinicPet, and Veterinarian collections are inverse sides.

### Fetch and Cascade Decisions
- Many-to-One relationships use LAZY fetching.
- Collections use the default LAZY strategy.
- PERSIST and MERGE are used where saving a parent should also save its dependent records.
- REMOVE is not cascaded to shared Veterinarian records.
- The design avoids deleting shared records accidentally.

### Smoke Test
The ClinicService creates and reads a sample Owner, Pet, Veterinarian, Appointment, and VaccinationRecord graph.