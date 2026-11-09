package com.example.tasktracker.week9.capstone;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clinic_vaccination_records")
public class VaccinationRecord {

    @EmbeddedId
    private VaccinationRecordId id;

    @MapsId("petId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private ClinicPet pet;

    public VaccinationRecord() {}

    public VaccinationRecord(String vaccineName, LocalDate vaccinationDate) {
        this.id = new VaccinationRecordId(null, vaccineName, vaccinationDate);
    }

    public VaccinationRecordId getId() { return id; }
    public ClinicPet getPet() { return pet; }
    public void setPet(ClinicPet pet) { this.pet = pet; }
}