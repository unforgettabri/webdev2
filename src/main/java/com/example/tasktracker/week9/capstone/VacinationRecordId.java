package com.example.tasktracker.week9.capstone;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class VaccinationRecordId implements Serializable {

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

    public VaccinationRecordId() {}

    public VaccinationRecordId(Long petId, String vaccineName, LocalDate vaccinationDate) {
        this.petId = petId;
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof VaccinationRecordId that)) return false;
        return Objects.equals(petId, that.petId)
            && Objects.equals(vaccineName, that.vaccineName)
            && Objects.equals(vaccinationDate, that.vaccinationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, vaccineName, vaccinationDate);
    }
}