package com.example.tasktracker.week9.capstone;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinic_pets")
public class ClinicPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String species;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "pet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "pet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<VaccinationRecord> vaccinationRecords = new ArrayList<>();

    public ClinicPet() {}

    public ClinicPet(String name, String species, Integer age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPet(this);
    }

    public void addVaccinationRecord(VaccinationRecord record) {
        vaccinationRecords.add(record);
        record.setPet(this);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public Integer getAge() { return age; }
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<VaccinationRecord> getVaccinationRecords() { return vaccinationRecords; }
}