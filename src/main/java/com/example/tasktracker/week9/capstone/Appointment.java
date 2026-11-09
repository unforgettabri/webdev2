package com.example.tasktracker.week9.capstone;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clinic_appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "reason_for_visit")
    private String reasonForVisit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private ClinicPet pet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vet_id", nullable = false)
    private Veterinarian veterinarian;

    public Appointment() {}

    public Appointment(LocalDate appointmentDate, String reasonForVisit,
                       Veterinarian veterinarian) {
        this.appointmentDate = appointmentDate;
        this.reasonForVisit = reasonForVisit;
        this.veterinarian = veterinarian;
    }

    public Long getId() { return id; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public String getReasonForVisit() { return reasonForVisit; }
    public ClinicPet getPet() { return pet; }
    public void setPet(ClinicPet pet) { this.pet = pet; }
    public Veterinarian getVeterinarian() { return veterinarian; }
}