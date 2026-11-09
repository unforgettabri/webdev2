package com.example.tasktracker.week9.capstone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRecordRepository
    extends JpaRepository<VaccinationRecord, VaccinationRecordId> {
}